<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    <title>系统菜单维护</title>
	<jsp:include page="../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		var recordNodes = new Array();//定义一个数组容器记录已被展开的节点ID
		$(function(){
            /* grid初始化 */
			$('#grid').treegrid({
						url : WEB_ROOT+'/cms/sysmenu/treegridData',
						//title : '系统菜单设置',
						idField : "id",
						treeField : "text",
						/* striped: true, */
	                    animate: false,//关闭动画效果
	                    nowrap: true,
						method : 'post',
						lines : true,
						singleSelect : true,
					    border:false,
					    fit:true,  
					    loadMsg: "加载中......",
					    //rownumbers:true,
						columns : [[
									{title : '菜单名', field : 'text', width : 180, align : 'left', editor : "textbox"},
									{title : '类型', field : 'category', width : 80, align : 'center', editor : "textbox"},
									{title : '菜单ID', field : 'id', width : 100, align : 'left', editor : "textbox"},
									{title : '父菜单ID', field : 'pid', width : 80, align : 'left', editor : "textbox"},
									{title : 'URL  /  方法', field : 'url', width : 160, align : 'left', editor : "textbox"},
									{title : '图标', field : 'iconCls', width : 160, align : 'left', editor : "textbox",hidden:true}
									]],
						toolbar : "#toolbar",
					    onDblClickRow : function(index, row){
					    	edit();
					    }, 
					    onBeforeExpand: function (node) {/*  */
			                recordNodes.push(node.id.toString());
					    },
					    onBeforeCollapse: function (node) {
			                var i = recordNodes.indexOf(node.id.toString());
			                if (i >= 0) { recordNodes.splice(i, 1); }
			            },
					    onLoadSuccess: function () {
			                var list = [];
			                for (var j = 0; j < recordNodes.length; j++) {
			                    list.push(recordNodes[j])
			                }
			                $('#grid').treegrid("collapseAll");
			                for (var i = 0; i < list.length; i++) {
			                	$('#grid').treegrid('expand', list[i]);
			                }
			                
			               /*  $('#grid').treegrid("collapseAll");//折叠所有节点
			    			//由根开始遍历一级一级的展开，展开到二级
			    		  	var roots=$('#grid').treegrid('getRoots'); 
			    		  	for(i=0;i<roots.length;i++){
			    		  		//alert(JSON.stringify(roots[i].id))
			    		  		$('#grid').treegrid('expand', roots[i].id);
			    		  	}    */
					    }
					});
			  
			
            
			//dlg初始化
	      	  $('#dlg').dialog({    
			    width: 400,    
			    height: 300,    
			    closed: true,    
			    cache: false,
			    buttons:'#dlg-buttons',    
			    modal: true   ,
			    inline:true,
			    border: 'thin'
			}); 
			
	      	
	      	//上级组件
	      	$('#pid').combotree({
				url : WEB_ROOT+'/cms/sysmenu/treegridData3level',
				parentField : 'pid',
				lines : true,
				panelHeight : 'auto',
				onLoadSuccess: function () {
					$("#pid").combotree('tree').tree("collapseAll");//默认加载完成后 全部折叠
			    }
			});
	      	
	        //图标选择框
	      	$('#iconCls').combotree({
	      		url : WEB_ROOT+ '/cms/sysmenu/gridIconPath',
				method:'get',
				editable: true,
                valueField: 'id',
                textField: 'text',
                width: 250,
                panelHeight: 200,
                onChange:function(newValue, oldValue){
                    //$("#deptId").val(newValue);    //赋值
                    //alert(newValue);
                    //alert(oldValue);
                } 
	      	}); 
	        //加载按钮()
	      	getButtons("${menuid}",$('#toolbar'),'toolbar');
	      	getButtons("${menuid}",$('#dlg-buttons'),'dlg-buttons');
	      	
		});
		
		
		//查询
		function query(){
			$('#grid').treegrid('reload');
		}
		//新增
		function add(){
			$('#dlg').dialog('open').dialog('setTitle','新增');
			$('#pid').combotree("reload");
			$('#fm').form('clear');
			$('#category').textbox("setValue","菜单");    //默认是 "菜单"
		}
		//编辑
		function edit(){
			var row = $('#grid').datagrid('getSelected');
			//alert(JSON.stringify(row));
			if (row){
				 $('#dlg').dialog('open').dialog('setTitle','编辑权限');
				 $('#pid').combotree("reload");
				 $('#fm').form('load',row);
			}
		}
		//删除
		function del(){
			/* 确认删除 */
			$.messager.confirm('确认对话框', '确认删除?', function(r){
				if (r){ 
					var row = $("#grid").datagrid('getSelected');
					if (row) {
						//var rowIndex = $("#grid").datagrid('getRowIndex', row);
						//$("#grid").datagrid('deleteRow', rowIndex);
						$.post(WEB_ROOT+ '/cms/sysmenu/del', { id : row.id }, 
								function(result) {
									if(result.success){ 
										$('#grid').treegrid('reload');
										$.messager.show({title: '提示',msg: result.msg,showType:'show'});
									}else{
										$.messager.show({title: '提示',msg: result.msg,showType:'show'});
									}
									
								}, "JSON");
					}	
				}
			});
			
		}
		//保存
		function save(){
			$('#fm').form('submit',{
				 url: WEB_ROOT+'/cms/sysmenu/save',
				 onSubmit: function(){
				 	//表单验证信息
				 	return $(this).form('validate');
				 },
				 success: function(result){
				 	 var result = eval('('+result+')');
					 if (result.success){
					 	 $('#dlg').dialog('close'); // close the dialog
						 $('#grid').treegrid('reload'); // reload the user data
						 $.messager.show({title: 'Success',msg: result.msg,showType:'show'});
					 } else {
						 $.messager.show({title: 'Error',msg: result.msg,showType:'show'});
					 }
				 }
			 });
		}
		//取消
		function cancel(){
			javascript:$('#dlg').dialog('close');
		}
	</script>
  </head>
   
  <body>
	<table id="grid"></table>
	
	<div id="toolbar"></div>
	
	<!-- 表单对话框 -->
 	<div id="dlg"> 
		<form:form modelAttribute="form" id="fm" method="post">
			<form:hidden path="id"/> 
			<form:hidden path="category"/> 
			<table cellpadding="5">
				<tr>
					<td>上级组件</td>
					<td>
						<form:input id="pid" path="pid" type="text" class="easyui-textbox" required="true" data-options="required:false"/>
						<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a>
					</td>
				</tr>
				<tr>
					<td>组件名称</td>
					<td><form:input id="text" path="text" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
				</tr>
				<tr>
					<td>路径/方法</td>
					<td><form:input id="url" path="url" type="text" class="easyui-textbox" required="true" data-options="required:false"/></td>
				</tr>
				<tr>
					<td>组件图标</td>
					<td><form:input id="iconCls" path="iconCls" type="text" class="easyui-combotree" required="true" data-options="required:false"/></td>
					<td></td>
				</tr>
			</table>
		</form:form> 
	</div>
	<div id="dlg-buttons"></div> 
	
  </body>
</html>
