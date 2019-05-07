<%@ page language="java" pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<%
	pageContext.setAttribute("APP_PATH",request.getContextPath());
%>

<link href="${APP_PATH }/static/jquery-easyui-1.5.1/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="${APP_PATH }/static/jquery-easyui-1.5.1/themes/icon.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${APP_PATH }/static/jquery-easyui-1.5.1/jquery.min.js"></script>
<script type="text/javascript" src="${APP_PATH }/static/jquery-easyui-1.5.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${APP_PATH }/static/jquery-easyui-1.5.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
//全局变量，存放站点的根目录
var contextPath='${pageContext.request.scheme}'+'://'+'${pageContext.request.serverName}'+':'+'${pageContext.request.serverPort}'+'${pageContext.request.contextPath}';

</script>