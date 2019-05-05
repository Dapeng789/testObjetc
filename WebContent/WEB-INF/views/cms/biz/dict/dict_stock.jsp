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
    <title>库房字典</title>
	<jsp:include page="../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
		var recordNodes = new Array();//定义一个数组容器记录已被展开的节点ID
		$(function(){
            /* grid初始化 */
			$('#grid').datagrid({
				url : WEB_ROOT+'/cms/biz/dict/stock/gridData',
				//title : '系统菜单设置',
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
							{title : '产地编码', field : 'sourceid',  align : 'left'},
							{title : '产地名称', field : 'sourcename',  align : 'left', editor : "textbox"},
							{title : '拼音', field : 'spell',  align : 'left', editor : "textbox"}
							]],
				toolbar : "#toolbar",
				onClickRow : function(index, row){
			    	endEdit();
			    },
			    onDblClickRow : function(index, row){
			    	endEdit();
			    	edit();
			    }
			});
		});
		
		
		//查询
		function query(){
			$('#grid').datagrid('reload');
		}
		//新增
		function add(){ 
			endEdit();
			$("#grid").datagrid('appendRow', {});
			var rows = $("#grid").datagrid('getRows');
			$("#grid").datagrid('beginEdit', rows.length - 1);
			$("#grid").datagrid('scrollTo', rows.length - 1);
			$("#grid").datagrid('selectRow', rows.length - 1);
		}
		//编辑
		function edit(){ 
			endEdit();
			var row = $("#grid").datagrid('getSelected');
			if (row) {
				var rowIndex = $("#grid").datagrid('getRowIndex', row);
				$("#grid").datagrid('beginEdit', rowIndex);
			}
		}
		//删除
		function del(){
			/* 确认删除 */
			$.messager.confirm('确认对话框', '确认删除?', function(r){
				if (r){ 
					endEdit();
					var row = $("#grid").datagrid('getSelected');
					if (row) {
						var rowIndex = $("#grid").datagrid('getRowIndex', row);
						$("#grid").datagrid('deleteRow', rowIndex);
					}	
				}
			});
			
		}
		//保存
		function save(){
			endEdit();
			if ($("#grid").datagrid('getChanges').length) {
				var changedRows = new Object();
				changedRows["inserted"] = $("#grid").datagrid('getChanges', "inserted");
				changedRows["deleted"] = $("#grid").datagrid('getChanges', "deleted");
				changedRows["updated"] = $("#grid").datagrid('getChanges', "updated");
				$.post(WEB_ROOT+'/cms/biz/dict/dictSource/save', { json : JSON.stringify(changedRows)}, 
						function(result) {
							if(result.success){ 
								$('#grid').datagrid('reload');
							}
							$.messager.show({title: '提示',msg: result.msg,showType:'show'});
						}, "JSON");
			}
		}
		//结束编辑
		function endEdit(){
			var rows = $("#grid").datagrid('getRows');
			for ( var i = 0; i < rows.length; i++) {
				$("#grid").datagrid('endEdit', i);
			}
		}
	</script>
  </head>
   
  <body>
	<table id="grid"></table>
	
	<div id="toolbar">
		<table cellpadding='1' cellspacing='1'>
			<tr>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-search' plain='true' onclick='query()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-add' plain='true' onclick='add()'>新增</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-remove' plain='true' onclick='del()'>删除</a></td>  
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-save' plain='true' onclick='save()'>保存</a></td>
				<td><div class='btn-separator'></div></td> 
				<!-- <td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon-redo' plain='true' onclick='exportExl()'>导出</a></td>
				<td><div class='btn-separator'></div></td>  -->
			</tr>
		</table>
	</div>
  </body>
</html>
