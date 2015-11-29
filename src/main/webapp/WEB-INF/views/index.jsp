<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/css/index.css">
</head>
<body style="width:100%;height: 100%;">

<div id="login">
    <p>账号：<input type="text" id="manager" class="textbox"></p>
    <p>密码：<input type="password" id="password" class="textbox"></p>
</div>

<div id="btn">
    <a href="#" class="easyui-linkbutton">登录</a>
</div>
<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/toDateString.js"></script>
<script type="text/javascript" src="${ctx}/js/index.js"></script>
</body>
</html>
