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
    <title>耗材分类维护</title>
	<jsp:include page="../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		var recordNodes = new Array();//定义一个数组容器记录已被展开的节点ID
		$(function(){
            /* grid初始化 */
			$('#grid').treegrid({
				url : WEB_ROOT+'/cms/biz/dict/classGoods/treegridData',
				//title : '系统菜单设置',
				idField : "id",
				treeField : "name",
				/* striped: true, */
                   animate: false ,//关闭动画效果
                   nowrap: true,
				method : 'post',
				lines : true,
				singleSelect : true,
			    border:false,
			    fit:true,  
			    loadMsg: "加载中......",
			    //rownumbers:true,
				columns : [[
							{title : '类型名称', field : 'name', width : 180, align : 'left', editor : "textbox"},
							{title : '类型编码', field : 'id', width : 60, align : 'left', editor : "textbox"},
							{title : '父级编码', field : 'pid', width : 50, align : 'left', editor : "textbox",hidden:true},
							{title : '拼音', field : 'spell', width : 160, align : 'left', editor : "textbox",hidden:true}
							]],
				toolbar : "#toolbar",
			    onDblClickRow : function(index, row){
			    	edit();
			    }, 
			    onBeforeExpand: function (node) {
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
			    },
			    onClickRow : function(index, row){
			    	edit();
			    }, 
			});
		});
		
		
		//查询
		function query(){
			$('#grid').treegrid('reload');
		}
		//新增
		function add(){ 
			$('#fm').form('clear');
			var  node= $('#grid').treegrid('getSelected');
			if(node){
				$('#pid').textbox("setValue",node.id);    
			}
		}
		//编辑
		function edit(){ 
			var node = $('#grid').treegrid('getSelected');
			$('#fm').form('clear');
			$('#fm').form('load',node);
		}
		//删除
		function del(){
			/* 确认删除 */
			$.messager.confirm('确认对话框', '确认删除?', function(r){
				if (r){ 
					var node = $("#grid").datagrid('getSelected');
					if (node) {
						$.post(WEB_ROOT+'/cms/biz/dict/classGoods/del', { id : node.id }, 
							function(result) {
								if(result.success){ 
									$('#grid').treegrid('reload');
									$('#fm').form('clear');
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
				 url: WEB_ROOT+'/cms/biz/dict/classGoods/save',
				 onSubmit: function(){
				 	//表单验证信息
				 	return $(this).form('validate');
				 },
				 success: function(result){
				 	 var result = eval('('+result+')');
					 if (result.success){
						 $('#grid').treegrid('reload'); // reload the user data
						 $.messager.show({title: 'Success',msg: result.msg,showType:'show'});
					 } else {
						 $.messager.show({title: 'Error',msg: result.msg,showType:'show'});
					 }
				 }
			 });
		}
		
	</script>
  </head>
   
  <body>
  	<div id="lo" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',title:'列表',collapsible:false,split:true,border:true" style="width:270px" >
			<table id="grid"></table>
		</div>  
		<!-- tree -->
		<div data-options="region:'center',title:'编辑',border:true"  >
			
			<form:form modelAttribute="form" id="fm" method="post">
				<table cellpadding="5" style="font-size:10;">
				<tr>
					<td>
						<a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='false' onclick='save()'>保存</a>
					</td>
				</tr>
				<tr>
					<td>上级类别:</td>
					<td>
						<form:input id="pid" path="pid" type="text" class="easyui-textbox"  data-options="required:false" readOnly="true" value=""/>
						<a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').textbox('setValue','');" >清空</a>
					</td>
				</tr>
				<tr>
					<td>类别编码:</td>
					<td>
						<form:input id="id" path="id" type="text" class="easyui-textbox"  data-options="required:false" readOnly="true"/>
					</td>
				</tr>
				<tr>
					<td>类别名称:</td>
					<td>
						<form:input id="name" path="name" type="text" class="easyui-textbox"  data-options="required:true"/>
					</td>
				</tr>
				<tr>
					<td>拼音:</td>
					<td>
						<form:input id="spell" path="spell" type="text" class="easyui-textbox" required="true" data-options="required:false"/>
					</td>
				</tr>
			</table>
		</form:form> 
		
		</div>  
	</div>
	
	<div id="toolbar">
		<table cellpadding='1' cellspacing='1'>
			<tr>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-search' plain='true' onclick='query()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='add()'>新增</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='del()'>删除</a></td>  
				<td><div class='btn-separator'></div></td>
				<!-- <td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='save()'>保存</a></td>
				<td><div class='btn-separator'></div></td> -->
				<!-- <td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a></td>
				<td><div class='btn-separator'></div></td>  -->
			</tr>
		</table>
	</div>
	
	
	
  </body>
</html>
