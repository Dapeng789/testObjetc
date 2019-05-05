<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>选择盘点库房</title>
		
</head>
	
<body >
	<!-- 数据列表 -->
	<table class="easyui-datagrid" style="width:400px;height:250px"  id="dg_store" 
        data-options="
        		url:'${pageContext.request.contextPath}/cms/common/storeSelect/datagrid',
        		fitColumns:true,
        		singleSelect:true,
        		toolbar:'#tb',
        		fit:true,
        		pagination:false,
        		border:false,
        		onDblClickRow:function(index, row){
        				selectStore();
        			}
        		"
        >   
    <thead>   
        <tr>   
            <th data-options="field:'storeid',width:80">库房ID</th>   
            <th data-options="field:'storename',width:120">库房名称</th>   
        </tr>   
    </thead>   
</table>  
	<!-- 表格的工具栏 -->
	<div id="tb">
		<table cellpadding='1' cellspacing='1'>   
			<tr>  
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_arrow_refresh' plain='true' onclick='queryStore()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='selectStore()'>选择</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='closeSelectStore()'>取消</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
		<%-- <form id="fm_storeSelect" method="post" >
			<div class="div-toolbar">
				名称<input  name="storenameselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
			</div> 
		</form> --%>
	</div>	
	<script type="text/javascript">  
			function queryStore(){
				var url='${pageContext.request.contextPath}/cms/common/storeSelect/datagrid?'+$('#fm_storeSelect').serialize();
				$("#dg_store").datagrid("reload",url)
			};
			function selectStore(){
				var row=$("#dg_store").datagrid("getSelected");
				if(row){
					getStore(row.storeid,row.storename);
					closeSelectStore();//选择完关闭
				}
			};
			function closeSelectStore(){
				//$(this).parent().window("close");
				$("#winStoreSelect").window("close");
			};
	</script> 
</body>
</html>