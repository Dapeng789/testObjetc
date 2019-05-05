<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
//EN">
<html>
  <head>
   <base href="<%=basePath%>">
    <title>报损报溢</title>
	<jsp:include page="../../../../inc/injs.jsp"/> 
	<script type="text/javascript"> 
	
	</script>
</head>
<body>
报损报溢
</body>
</html>