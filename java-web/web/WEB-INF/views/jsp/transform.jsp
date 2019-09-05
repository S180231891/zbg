<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/2/23
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>重复提交表单</title>
</head>
<body>
<form action="/out" method="post">
<input type="hidden" name="token" value="${token}">
转账金额：<input type="number" name="money" required>
<input type="submit" value="确认转账">
</form>
</body>
</html>
