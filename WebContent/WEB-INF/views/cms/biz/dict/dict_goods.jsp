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
    <title>耗材字典维护</title>
	<jsp:include page="../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		var recordNodes = new Array();//定义一个数组容器记录已被展开的节点ID
		$(function(){
			$('#grid1').treegrid({
				url : WEB_ROOT+'/cms/biz/dict/goods/treegrid1Data',
				//title : '系统菜单设置',
				idField : "id",
				treeField : "name",
				/* striped: true, */
				title:'类别',
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
	                $('#grid1').treegrid("collapseAll");
	                for (var i = 0; i < list.length; i++) {
	                	$('#grid1').treegrid('expand', list[i]);
	                }
			    },
			    onDblClickRow : function( row){
			    	$('#grid2').datagrid('reload',WEB_ROOT+'/cms/biz/dict/goods/grid2Data?classid='+row.id);
			    }, 
			});
			$('#grid2').datagrid({
				url : '',
				singleSelect:true,
			    striped:true,
			    fit:true,
			    border:true,
			    rownumbers:true,
			    title:'耗材列表',
			    toolbar: "#toolbar",
			    loadMsg: "加载中......",
			    //rownumbers:true,
				columns : [[
					{title:'耗材编码',field:'goodsid',align:'left'},
					{title:'耗材名称',field:'goodsname',width:180,align:'left'},
					{title:'通用名称',field:'commonname',width:180,align:'left'},
					{title:'分类编码',field:'classid',align:'left'},
					{title:'拼音',field:'spell',align:'left'},
					/* {title:'规格',field:'spec',align:'left'},
					{title:'单位',field:'unit',align:'left'}, */
					{title:'是否高值耗材',field:'ishigh',align:'left'},
					{title:'是否停用',field:'isstoped',align:'left'},
					{title:'是否计费',field:'ischarged',align:'left'},
					{title:'是否库存',field:'isstocked',align:'left'},
					{title:'是否自动审核',field:'isaudited',align:'left'},
					{title:'备注',field:'memo',width:180,align:'left'},
					{title:'生产厂家',field:'productid',width:180,align:'left'},
					{title:'源产地',field:'sourceid',width:180,align:'left'},
				]],
				onDblClickRow : function( row){
			    	edit();
			    }, 
			});
			
			$('#grid_pkg').datagrid({
				url : '',
				singleSelect:true,
			    striped:true,
			    fit:true,
			    border:true,
			    rownumbers:true, 
			    //title:'耗材包装明细',
			    toolbar: "#toolbar_pkg",
			    loadMsg: "加载中......",
			    //rownumbers:true,
				columns : [[
							{title:'序号',field:'sortno',align:'left'},
							{title:'耗材明细编码',field:'goodsdtlid',align:'left'},
							{title:'耗材编码',field:'goodsid',align:'left'},
							{title:'单位',field:'unitname',align:'left'},
							{title:'单位ID',field:'unitid',align:'left',hidden:true},
							{title:'规格',field:'spec',align:'left'},
							{title:'比率',field:'ratio',align:'left'},
							{title:'状态',field:'status',align:'left'},
				]],
				onDblClickRow : function( row){
			    	//edit();
			    }, 
			});
			
			//dlg初始化
	      	  $('#dlg').dialog({    
			    fit:true,   
			    closed: true,    
			    cache: false,
			    toolbar:'#dlg-buttons',    
			    modal: true   ,
			    inline:true,
			    border: false
			}); 
		});
		
		
		
		//新增
		function add(){ 
			$('#dlg').dialog('open').dialog('setTitle','新增');
			$('#fm').form('clear');
			//刷新 grid_pkg
			$("#grid_pkg").datagrid("load",WEB_ROOT+'/cms/biz/dict/goods/gridDataPkg?goodsid=-1');
		}
		//编辑
		function edit(){ 
			var row = $('#grid2').datagrid('getSelected');
			//alert(JSON.stringify(row));
			if (row){
				 $('#dlg').dialog('open').dialog('setTitle','编辑');
				 $('#fm').form('load',row);
				 //刷新 grid_pkg
				 $("#grid_pkg").datagrid("load",WEB_ROOT+'/cms/biz/dict/goods/gridDataPkg?goodsid='+row.goodsid);
			}
		}
		//删除
		function del(){
			/* 确认删除 */
			/* $.messager.confirm('确认对话框', '确认删除?', function(r){
				if (r){ 
					var node = $("#grid1").datagrid1('getSelected');
					if (node) {
						$.post(WEB_ROOT+'/cms/biz/dict/goods/del', { id : node.id }, 
							function(result) {
								if(result.success){ 
									$('#grid1').treegrid1('reload');
									$('#fm').form('clear');
									$.messager.show({title: '提示',msg: result.msg,showType:'show'});
								}else{
									$.messager.show({title: '提示',msg: result.msg,showType:'show'});
								}
								
						}, "JSON");
					}	
				}
			}); */
		}
		//保存
		function save(){
			/* $('#fm').form('submit',{
				 url: WEB_ROOT+'/cms/biz/dict/goods/save',
				 onSubmit: function(){
				 	//表单验证信息
				 	return $(this).form('validate');
				 },
				 success: function(result){
				 	 var result = eval('('+result+')');
					 if (result.success){
						 $('#grid1').treegrid1('reload'); // reload the user data
						 $.messager.show({title: 'Success',msg: result.msg,showType:'show'});
					 } else {
						 $.messager.show({title: 'Error',msg: result.msg,showType:'show'});
					 }
				 }
			 }); */
		}
		
	</script>
  </head>
   
  <body>
  	<div id="lo" class="easyui-layout" data-options="fit:true,border:false">
		<div data-options="region:'west',collapsible:false,split:true,border:true" style="width:270px" >
			<table id="grid1"></table>
		</div>  
		<!-- tree -->
		<div data-options="region:'center',border:false"  >
			<table id="grid2"></table>
		</div>  
	</div>
	<div id="toolbar">
		<table cellpadding='1' cellspacing='1'>
			<tr>
				<td><div class='btn-separator'></div></td> 
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='add()'>新增</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='del()'>删除</a></td>  
				<td><div class='btn-separator'></div></td> 
			</tr>
		</table>
	</div>
	
	<!-- 表单对话框 -->
 	<div id="dlg"> 
 		<div class="easyui-layout" data-options="fit:true,border:false">
	 		<div data-options="region:'west',collapsible:false,split:true,border:true" style="width:600px" >
				<form:form modelAttribute="form" id="fm" method="post">
					<form:hidden path="goodsid"/> 
					<table cellpadding="5" style="font-size:12">
						<tr>
							<td>
								<table cellpadding='1' cellspacing='1'>
									<td><a href="javascript:void(0);" class="easyui-linkbutton" plain='true' iconCls="icon-ok" onclick="save()">保存</a></td>
									<td><div class='btn-separator'></div></td> 
									<td><a href="javascript:void(0);" class="easyui-linkbutton" plain='true' iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a></td>
								</table>
							</td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td>耗材名称:</td>
							<td><form:input path="goodsname" type="text" class="easyui-textbox"  data-options="required:true"/></td>
							<td>通用名称:</td>
							<td><form:input path="commonname" type="text" class="easyui-textbox"  data-options="required:false"/></td>
						</tr>
						<tr>
							<td>耗材分类:</td>
							<td><form:input path="classid" type="text" class="easyui-textbox"  data-options="required:true"/></td>
							<td>拼音:</td>
							<td><form:input path="spell" type="text" class="easyui-textbox"  data-options="required:true"/></td>
						</tr>
						<%-- <tr>
							<td>规格:</td>
							<td><form:input path="spec" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
							<td>单位:</td>
							<td><form:input path="unit" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
						</tr> --%>
						<tr>
							<td>是否高值:</td>
							<td><form:input path="ishigh" type="text" class="easyui-textbox" data-options="required:true"/></td>
							<td>是否停用:</td>
							<td><form:input path="isstoped" type="text" class="easyui-textbox" data-options="required:true"/></td>
						</tr>
						<tr>
							<td>是否计费:</td>
							<td><form:input path="ischarged" type="text" class="easyui-textbox" data-options="required:true"/></td>
							<td>是否库存:</td>
							<td><form:input path="isstocked" type="text" class="easyui-textbox" data-options="required:true"/></td>
						</tr>
						<tr>
							<td>是否自动审核:</td>
							<td><form:input path="isaudited" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
							<td>生产厂家:</td>
							<td><form:input path="productid" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
						</tr>
						<tr>
							<td>产地:</td>
							<td><form:input path="sourceid" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
							<td>备注:</td>
							<td><form:input path="memo" type="text" class="easyui-textbox" required="true" data-options="required:true"/></td>
						</tr>
					</table>
					
				</form:form> 
			</div>  
			<div data-options="region:'center',border:true"  >
				<table id="grid_pkg"></table>
			</div> 
 		</div>
	</div>
	<div id="toolbar_pkg">
		<table cellpadding='1' cellspacing='1'>
			<tr>
				<td><div class='btn-separator'></div></td> 
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick=''>新增</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick=''>删除</a></td>  
				<td><div class='btn-separator'></div></td> 
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick=''>保存</a></td>  
				<td><div class='btn-separator'></div></td> 
			</tr>
		</table>
		
		<form id="fmQuery" method="post" style="border-top: 1px #DDDDDD solid">
			<div style="padding-left: 10px; padding-top: 10px;">
				用户名<input id="realname" name="realname" class="easyui-textbox"
					style="width: 80px">&nbsp;&nbsp; 登录名<input id="username"
					name="username" class="easyui-textbox" style="width: 80px">&nbsp;&nbsp;
			</div>
		</form>
	</div>
	<!-- <div id="dlg-buttons">
		<table cellpadding='1' cellspacing='1'>
			<td><div class='btn-separator'></div></td> 
			<td><a href="javascript:void(0);" class="easyui-linkbutton" plain='true' iconCls="icon-ok" onclick="save()">保存</a></td>
			<td><div class='btn-separator'></div></td> 
			<td><a href="javascript:void(0);" class="easyui-linkbutton" plain='true' iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close');">取消</a></td>
			<td><div class='btn-separator'></div></td> 
		</table>
	</div>  -->
  </body>
</html>
