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
    <title>选择货品</title>
		
</head>
	
<body >
	<!-- 数据列表 -->
	<table class="easyui-datagrid" style="width:400px;height:250px"  id="dg_goods" 
        data-options="
        		url:'${pageContext.request.contextPath}/cms/common/goodsSelect/datagrid',
        		fitColumns:true,
        		singleSelect:true,
        		toolbar:'#tb',
        		fit:true,
        		pagination:true,
        		border:false,
        		onDblClickRow:function(index, row){
        				selectGoods();
        			}
        		"
        >   
    <thead>   
        <tr>   
            <th data-options="field:'goodsid',width:20,hidden:true">主档编码</th>   
            <th data-options="field:'goodsdtlid',width:20,hidden:true">包装编码</th>   
            <th data-options="field:'goodsname',width:20">名称</th>   
            <th data-options="field:'customname',width:20">别名</th>   
            <th data-options="field:'spec',width:20">规格</th>    
            <th data-options="field:'unit'">单位</th>      
            <th data-options="field:'classname',width:20">类别</th>  
        </tr>   
    </thead>   
</table>  
	<!-- 表格的工具栏 -->
	<div id="tb">
		<table cellpadding='1' cellspacing='1'>   
			<tr>  
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_arrow_refresh' plain='true' onclick='queryGoods()'>查询</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_tick' plain='true' onclick='selectGoods()'>选择</a></td>
				<td><div class='btn-separator'></div></td>
				<td><a href='javascript:void(0);' class='easyui-linkbutton' iconCls='icon_cross' plain='true' onclick='closeSelectGoods()'>取消</a></td>
				<td><div class='btn-separator'></div></td>
			</tr> 
		</table>
		<form id="fm_goodsSelect" method="post" >
			<div style="padding-left:10px; padding-top:10px;">
				名称<input  name="goodsnameselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
				拼音<input  name="goodsspellselect" class="easyui-textbox" style="width:80px">&nbsp;&nbsp;
			</div> 
		</form>
	</div>	
	<script type="text/javascript">  
			function queryGoods(){
				var url='${pageContext.request.contextPath}/cms/common/goodsSelect/datagrid?'+$('#fm_goodsSelect').serialize();
				$("#dg_goods").datagrid("reload",url)
			};
			function selectGoods(){
				var row=$("#dg_goods").datagrid("getSelected");
				if(row){
					getGoodsid(row.goodsid,row.goodsdtlid);
					closeSelectGoods();//选择完关闭
				}
			};
			function closeSelectGoods(){
				//$(this).parent().window("close");
				$("#winGoodsSelect").window("close");
			};
	</script> 
</body>
</html>