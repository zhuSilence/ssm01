<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhuxiang
  Desc : 
  Date: 2015/11/28
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<link href="${ctx}/js/uploadify/uploadify.css" rel="stylesheet">--%>
<input type="hidden" value="${ctx}" id="ctx">
<table id="table-logData"></table>
<div id="tb" style="padding: 5px;">
    <div style="margin-bottom: 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add"    plain="true" onclick="obj_logData.add();">增加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit"   plain="true" onclick="obj_logData.edit();">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj_logData.remove();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save"   plain="true" style="display: none;" id="save-logData" onclick="obj_logData.save();">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo"   plain="true" style="display: none;" id="redo-logData" onclick="obj_logData.redo();">取消编辑</a>
    </div>
    <div style="padding: 0 0 0 7px; color: #333;">
        查询测试人：<input type="text" name="tester" id="tester" class="textbox" style="width:110px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj_logData.search();">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean">清空</a>
    </div>
</div>

<script src="${ctx}/js/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript" src="${ctx}/js/logDataList.js"></script>



