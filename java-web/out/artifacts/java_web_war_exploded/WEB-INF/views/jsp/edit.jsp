<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/2/21
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>保存修改页面</title>
</head>
<body>
用户登录信息：${sessionScope.USER_SESSION}
<form action="/user?cmd=save" method="post">
    <table border="1" cellspacing="0" cellpadding="0" >
        <input type="hidden" name="id" value="${u.id}">
        <tr>
            <td>name:</td>
            <td><input type="text" name="name" value="${u.name}"></td>
        </tr>
        <tr>
            <td>age:</td>
            <td><input type="text" name="age" value="${u.age}"></td>
        </tr>
        <tr>
            <td>sex:</td>
            <td><input type="text" name="sex" value="${u.sex}"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value='${u==null?"保存数据":"修改数据"}'></td>
        </tr>
    </table>
</form>
</body>
</html>
