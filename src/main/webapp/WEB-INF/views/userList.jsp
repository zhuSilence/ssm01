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

<table id="table"></table>
<div id="tb" style="padding: 5px;">
    <div style="margin-bottom: 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add"    plain="true" onclick="obj.add();">增加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit"   plain="true" onclick="obj.edit();">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj.remove();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save"   plain="true" style="display: none;" id="save" onclick="obj.save();">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo"   plain="true" style="display: none;" id="redo" onclick="obj.redo();">取消编辑</a>
    </div>
    <div style="padding: 0 0 0 7px; color: #333;">
        查询账号：<input type="text" name="username" id="username" class="textbox" style="width:110px;">
        创建时间从：<input type="text" name="date_from" id="date_from" class="easyui-datebox" editable="false" style="width:110px;"> 到 <input type="text" name="date_to" editable="false" id="date_to" class="easyui-datebox" style="width:110px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj.search();">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean">清空</a>
    </div>
</div>

<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/userList.js"></script>



