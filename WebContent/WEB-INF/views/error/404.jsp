<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<style>
html {
  background: url(${pageContext.request.contextPath}/img/404.jpg) no-repeat center center fixed ;
  -moz-background-size:100% 100%; 
  background-size:100% 100%;
  
  -webkit-background-size: cover;
  -o-background-size: cover;
} 
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>