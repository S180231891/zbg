<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2019/2/21
  Time: 8:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Java-web</title>
</head>
<body>

用户登录信息：${sessionScope.USER_SESSION}
<%--<a href="WEB-INF/views/jsp/login.jsp">注销登录</a>--%>
<%--超链接和重定向均不能访问WEB-INF的jsp--%>
<form action="/user" method="post">
    搜索名称：<input type="text" name="name" value="${query.name}">
    最小值：<input type="number" name="minAge" value="${query.minAge}">
    最大值：<input type="number" name="maxAge" value="${query.maxAge}">
    <input type="submit" value="搜索">
<a href="/user?cmd=edit">增加商品</a>
    <table border="1" cellpadding="0" cellspacing="0" width="50%" align="center">
        <tr style="color: red">
            <th>id</th>
            <th>name</th>
            <th>age</th>
            <th>sex</th>
            <th colspan="2">操作方式</th>
        </tr>
        <C:forEach items="${u}" var="user" varStatus="vc">
            <tr style="background-color: ${vc.count%2==0?"aqua":""}">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.age}</td>
                <td>${user.sex}</td>
                <td><a href="/user?cmd=edit&id=${user.id}">修改</a> </td>
                <td><a href="/user?cmd=delete&id=${user.id}">删除</a> </td>
            </tr>
        </C:forEach>
    </table>
</form>
</body>
</html>
