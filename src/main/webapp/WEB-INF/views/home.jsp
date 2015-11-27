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
    <script type="text/javascript" src="${ctx}/js/toDateString.js"></script>
    <script type="text/javascript" src="${ctx}/js/home.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/default/easyui.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/js/easyui/themes/icon.css">
    <link type="text/css" rel="stylesheet" href="${ctx}/css/home.css">
</head>

<%--<body class="easyui-layout">
    <div id="north" data-options="region:'north'" style="height: 100px;">
        <div id="info">
            <a href="${ctx}/login/loginOut.action" class="member">注销</a>
            <a href="###" class="member">个人中心</a>
            <a href="###">欢迎您，${user.username}</a>
        </div>
    </div>
    <div id="west" data-options="region:'west',title:'West',split:true" style="width:200px;">

    </div>
    <div id="center" data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;"></div>
    <div id="south" data-options="region:'south'" style="height: 80px;">
        <span>Copyright © 2015 湖北工业大学计算机学院</span>
    </div>
</body>--%>

<body>

<%--<table class="easyui-datagrid">
    <thead>
    <tr>
        <th data-options="field:'id'">编号</th>
        <th data-options="field:'username'">用户名</th>
        <th data-options="field:'password'">密码</th>
        <th data-options="field:'salt'">盐值</th>
        <th data-options="field:'locked'">是否锁定</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>1</td>
            <td>silence</td>
            <td>123456</td>
            <td>haha</td>
            <td>否</td>
        </tr>
        <tr>
            <td>2</td>
            <td>朱翔</td>
            <td>123456</td>
            <td>54321</td>
            <td>否</td>
        </tr>
    </tbody>
</table>--%>

<table id="table"></table>
<div id="tb" style="padding: 5px;">
    <div style="margin-bottom: 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="obj.add();">增加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="obj.edit();">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj.remove();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" style="display: none;" id="save" onclick="obj.save();">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo" plain="true" style="display: none;" id="redo" onclick="obj.redo();">取消编辑</a>
    </div>
    <div style="padding: 0 0 0 7px; color: #333;">
        查询账号：<input type="text" name="username" id="username" class="textbox" style="width:110px;">
        创建时间从：<input type="text" name="date_from" id="date_from" class="easyui-datebox" editable="false" style="width:110px;"> 到 <input type="text" name="date_to" editable="false" id="date_to" class="easyui-datebox" style="width:110px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj.search();">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean">清空</a>
    </div>
</div>
</body>
</html>


