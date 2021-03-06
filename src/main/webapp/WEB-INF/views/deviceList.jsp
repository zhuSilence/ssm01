<%--
  Created by IntelliJ IDEA.
  User: Tao
  Date: 2015/11/29
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div id="tb-device" style="padding: 5px;">
  <div style="margin-bottom: 5px;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add"    plain="true" onclick="obj_device.add();">增加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit"   plain="true" onclick="obj_device.edit();">修改</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj_device.remove();">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save"   plain="true" style="display: none;" id="save_device" onclick="obj_device.save();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-redo"   plain="true" style="display: none;" id="redo_device" onclick="obj_device.redo();">取消编辑</a>
  </div>
  <div style="padding: 0 0 0 7px; color: #333;">
    设备名称：<input type="text" name="d_name" id="d_name" class="textbox" style="width:110px;">
    价格范围：<input type="text" name="low_price" id="low_price" > 到 <input type="text" name="high_price" id="high_price">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj_device.search();">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean-divice">清空</a>
  </div>
</div>

<table id="table-device"></table>

<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/deviceList.js"></script>