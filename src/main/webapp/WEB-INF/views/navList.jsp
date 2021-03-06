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

<table id="table-nav"></table>
<div id="tb-nav" style="padding: 5px;">
    <div style="margin-bottom: 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add"    plain="true" onclick="obj_nav.add();">增加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit"   plain="true" onclick="obj_nav.edit();">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj_nav.remove();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save"   plain="true" style="display: none;" id="save-nav" onclick="obj_nav.save();">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo"   plain="true" style="display: none;" id="redo-nav" onclick="obj_nav.redo();">取消编辑</a>
    </div>
    <div style="padding: 0 0 0 7px; color: #333;">
        导航名称：<input type="text" name="text" id="text" class="textbox" style="width:110px;">
        导航状态：
        <select id="state" class="easyui-combobox" data-options="panelHeight : 80," name="state" style="width:110px;">
            <option value="">--请选择--</option>
            <option value="open">open</option>
            <option value="closed">closed</option>
        </select>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj_nav.search();">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean-nav">清空</a>
    </div>
</div>

<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/navList.js"></script>



