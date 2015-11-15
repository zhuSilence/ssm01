<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<body>
<h2>登录页面</h2>
<form action="${ctx}/login/login.action" method="post">
    用户名：<input type="text" name="userName"/>
    密　码：<input type="password" name="password"/>
    <input type="submit" value="登录"/>
</form>

</body>
</html>
