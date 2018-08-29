<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018\8\29 0029
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>新增用户</title>
</head>
<body>
<form method="post" action="<c:url value="/user/login.html"/>">
    <table>
        <tr>
            <td>用户名：</td>
            <td><input type="text" name="userName"  value="${user.userName}"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input type="password" name="password" value="${user.password}"/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="login"></td>
        </tr>
    </table>
</form>
</body>
</html>