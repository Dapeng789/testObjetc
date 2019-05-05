<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
<META HTTP-EQUIV="Expires" CONTENT="0" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<script>
	var WEB_ROOT = "${pageContext.request.contextPath}";
	var LANG = "${pageContext.request.locale}";
</script>


<!--JQuey库 (必须)-->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.11.2.min.js"></script>

<!-- easyui -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/datagrid-filter.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/themes/icon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/themes/color.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/jquery.edatagrid.js">
<!-- easyui主题 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/themes/uimaker/easyui.css">


<!-- 引用insdep主题 -->
<%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/insdep.extend.min.js"> 
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/insdep.easyui.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/InsdepUI-for-EasyUI-1.1.0/insdep.theme_default.css"> --%>

<!-- easyui扩展 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/icon.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/jquery-easyui-datagridview/datagrid-detailview.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/jquery-easyui-datagridview/datagrid-groupview.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/easyui-datagrid-moverow.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/js/jquery-easyui-1.6.10/btn-separator.css" />


<!-- 其他  -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.serialize-object.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/crypto/core-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/crypto/enc-base64-min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/my.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pinyin.js" charset="GBK"></script>

<!-- font-awesome -->
<link
	href="${pageContext.request.contextPath}/js/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet">

<!-- 二维码 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.qrcode.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/qrcode.min.js"></script>

<!-- 条形码 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/JsBarcode.all.min.js"></script>


<!-- 二维码字符串压缩 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pako/pako.js"></script>

<!-- 打印控件 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery.jqprint-0.3.js"></script>

<!-- 按钮控制 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/buttons.js"></script>

<!-- 公共资源 -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/pub.js"></script>
<!-- 自定义页面滚动条 -->
<%-- 
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/malihu-custom-scrollbar-plugin-3.1.5/jquery.mCustomScrollbar.css" />
<script src="${pageContext.request.contextPath}/js/malihu-custom-scrollbar-plugin-3.1.5/jquery.mCustomScrollbar.concat.min.js"></script>
<script src="${pageContext.request.contextPath}/js/malihu-custom-scrollbar-plugin-3.1.5/jquery.mCustomScrollbar.js"></script>
 --%>


