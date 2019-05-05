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
    <title>选择库存明细</title>
		
</head>
	
<body >
	<!-- 数据列表 -->
	<table class="easyui-datagrid" style="width:400px;height:250px"  id="dg_stock" 
        data-options="
        		url:'${pageContext.request.contextPath}/cms/common/goodsStockSelectDtl/datagrid?storeid='+${storeid },
        		fitColumns:true,
        		singleSelect:true,
        		toolbar:'#tb',
        		fit:true,
        		pagination:false,
        		border:false,
        		onDblClickRow:function(index, row){
        				selectStock();
        			}
        		"
        >   
    <thead>   
        <tr>    
            <th data-options="field:'lotid'">批次</th>      
            <th data-options="field:'goodsid',width:20,hidden:true">主档编码</th>   
            <th data-options="field:'goodsdtlid',width:20,hidden:true">包装编码</th>   
            <th data-options="field:'goodsname'">名称</th>   
            <th data-options="field:'customname'">别名</th>   
            <th data-options="field:'spec'">规格</th>    
            <th data-options="field:'qty'">库存</th>    
            <th data-options="field:'unitname'">单位</th>      
            <th data-options="field:'classname'">类别</th>  
            <th data-options="field:'factoryname'">厂家</th>      
            <th data-options="field:'prodareaname'">产地</th>      
            <th data-options="field:'bathno'">生产批号</th>      
            <th data-options="field:'prodate'">生产日期</th>      
            <th data-options="field:'expiredate'">有效期</th>    
        </tr>   
    </thead>   
</table>  
	<!-- 表格的工具栏 -->
	<div id="tb">
		<table cellpadding='1' cellspacing='1'>   
			<tr>  
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_arrow_refresh' plain='true' onclick='queryStock()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='selectStock()'>选择</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='closeSelectStock()'>取消</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
		<form id="fm_stockSelect" method="post" >
			<div style="padding-left:10px; padding-top:10px;">
				名称<input  name="goodsnameselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
				拼音<input  name="goodsspellselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
			</div> 
		</form>
	</div>	
	<script type="text/javascript">  
			function queryStock(){
				var url='${pageContext.request.contextPath}/cms/common/goodsStockSelectDtl/datagrid?storeid="${storeid}"&'+$('#fm_stockSelect').serialize();
				$("#dg_stock").datagrid("reload",url)
			};
			function selectStock(){
				var row=$("#dg_stock").datagrid("getSelected");
				if(row){
					getStockGoodsid(row.stockid,row.lotid,row.storeid,row.goodsid,row.goodsdtlid);
					closeSelectStock();//选择完关闭
				}
			};
			function closeSelectStock(){
				//$(this).parent().window("close");
				$("#winStockSelect").window("close");
			};
	</script> 
</body>
</html>