<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 08.12.2020
  Time: 02:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

<form:form modelAttribute="user" method="post" action="/login">
<%--    <form:hidden path="id" />--%>
    <p>User Comit ID <form:input path="comitId" placeholder="9 digits"/></p>
    <p>Password <form:password showPassword="false" path="password" /></p>
    <button type="submit">Submit</button>
</form:form>

</body>
</html>
