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

<table id="table-using"></table>
<div id="tb-using" style="padding: 5px;">
    <div style="margin-bottom: 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add"    plain="true" onclick="obj_using.add();">增加</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit"   plain="true" onclick="obj_using.edit();">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj_using.remove();">删除</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-save"   plain="true" style="display: none;" id="save-using" onclick="obj_using.save();">保存</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-redo"   plain="true" style="display: none;" id="redo-using" onclick="obj_using.redo();">取消编辑</a>
    </div>
    <div style="padding: 0 0 0 7px; color: #333;">
        设备名称：<input type="text" name="d_name" id="dName" class="textbox" style="width:110px;">
        所在位置：<input type="text" name="u_place" id="uPlace" class="textbox" style="width:110px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj_using.search();">查询</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean-using">清空</a>
    </div>
</div>

<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/usingList.js"></script>



