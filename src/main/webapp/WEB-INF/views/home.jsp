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
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/css/home.css">
</head>

<body class="easyui-layout">
    <div id="north" data-options="region:'north'" style="height: 100px;">
        <div id="info">
            <a href="${ctx}/login/loginOut.action" class="member">注销</a>
            <a href="###" class="member">个人中心</a>
            <a href="###">欢迎您，${user.username}</a>
        </div>
    </div>
    <div id="west" data-options="region:'west',title:'导航',split:true,iconCls : 'icon-world'" style="width:180px;padding: 10px;">
        <ul id="nav"></ul>
    </div>
    <div id="center" data-options="region:'center'" style="padding:5px;background:#eee;">
        <div id="tabs">
            <div title="起始页" iconCls = "icon-house" style="padding: 0 10px; display: block;">
                欢迎来到设备管理系统
            </div>
        </div>
    </div>
    <div id="south" data-options="region:'south'" style="height: 60px;">
        <span>Copyright © 2015 湖北工业大学计算机学院</span>
    </div>
</body>

<body>


<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/js/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/js/toDateString.js"></script>
<script type="text/javascript" src="${ctx}/js/home.js"></script>
</body>
</html>


