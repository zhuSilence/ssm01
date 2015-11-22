<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhuxiang
  Desc : 
  Date: 2015/11/21
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>首页</title>
    <script type="text/javascript" src="${ctx}/js/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/js/home.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/css/home.css">
</head>
<body class="easyui-layout">
    <div id="north" data-options="region:'north'" style="height: 100px;">
        <div id="info">
            <a href="###" class="member">注销</a>
            <a href="###" class="member">个人中心</a>
            <a href="###">欢迎您，${user.username}</a>
        </div>
    </div>
    <div data-options="region:'west',title:'West',split:true" style="width:200px;"></div>
    <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
    <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
</body>
</html>


