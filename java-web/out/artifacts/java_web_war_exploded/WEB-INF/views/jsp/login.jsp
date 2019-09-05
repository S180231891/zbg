<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/2/21
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
    <%
        //销毁session中的缓存
        session.invalidate();
    %>
</head>
<body>
<form action="/login" method="post">
<span style="color: red">${errorMsg}</span>
    <table border="1" cellpadding="0" cellspacing="0">
        <tr>
            <td>username:</td>
            <td><input type="text" name="username" value="${user.username}"></td>
        </tr>
        <tr>
            <td>password:</td>
            <td><input type="text" name="password" value="${user.password}"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="登录"></td>
        </tr>
    </table>
</form>
</body>
</html>
