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
    
    <title>系统参数维护</title>
    
	
	<jsp:include page="../../inc/injs.jsp"/> 
	<script type="text/javascript">
        $(function(){ 
        	//数据表部分初始化
       	 $('#dg1').datagrid({
       		url:WEB_ROOT+'/cms/user/gridData',
			    columns:[[
			        {field:'userid',title:'用户编码',width:100,align:'center' },    
			        {field:'realname',title:'真实名',width:100,align:'center',editor : "textbox" },    
			        {field:'username',title:'登录名',width:100,align:'center',editor : "textbox"} 
			    ]],
			    singleSelect:true,
			    striped:true,
			    fit:true,
			    border:false,
			    //title:'用户列表',
			    rownumbers:true,
			    toolbar: "#toolbar",
				pagination : true, 
				pageSize : 10,	
				pageList : [10,20,30] ,
			    pageNumber:1,   
			    rownumbers:true,
			    onClickRow : function(index, row){
			    	endEdit();
			    },
			    onDblClickRow : function(index, row){
			    	endEdit();
			    	editUser();
			    }
       	 
			});  
        	$('#tt').tabs({    
        	    border:false, 
        	    fit:true,
        	    border:true,
        	    onSelect:function(title,index){    
        	        if(index==0){
        	        	
        	        } else if(index==1){
        	        	
        	        } 
        	    }    
        	}); 
        	//加载按钮()
 	      	getButtons("${menuid}",$('#toolbar'),'toolbar');
		});
		
    </script>
  </head>
  
  <body>
  	<div id="tt" >   
	    <div title="动态参数设置" >   
	        <table id="dg1"></table> 
	    </div>   
	    <div title="静态参数设置" >   
	        tab2    
	    </div>   
	</div>  
  </body>
</html>
