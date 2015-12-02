<%--
  Created by IntelliJ IDEA.
  User: Tao
  Date: 2015/12/2
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>


<div id="tb-buydivice" style="padding: 5px;">
  <div style="margin-bottom: 5px;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add"    plain="true" onclick="obj.add();">增加</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit"   plain="true" onclick="obj.edit();">修改</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="obj.remove();">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save"   plain="true" style="display: none;" id="save" onclick="obj.save();">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-redo"   plain="true" style="display: none;" id="redo" onclick="obj.redo();">取消编辑</a>
  </div>
  <div style="padding: 0 0 0 7px; color: #333;">
    购买人：<input type="text" name="buyer" id="buyer" class="textbox" style="width:110px;">
    价格范围：<input type="text" name="low_money" id="low_money" > 到 <input type="text" name="high_money" id="high_money">
    购买时间从：<input type="text" name="b_time_from" id="b_time_from" class="easyui-datebox" editable="false" style="width:110px;"> 到 <input type="text" name="b_time_to" editable="false" id="b_time_to" class="easyui-datebox" style="width:110px;">
    <a href="#" class="easyui-linkbutton" iconCls="icon-search" plain="true" onclick="obj.search();">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-no" plain="true" id="clean-buydivice">清空</a>
  </div>
</div>

<table id="table-buydevice"></table>

<input type="hidden" value="${ctx}" id="ctx">
<script type="text/javascript" src="${ctx}/js/buydeviceList.js"></script>

</body>
</html>
