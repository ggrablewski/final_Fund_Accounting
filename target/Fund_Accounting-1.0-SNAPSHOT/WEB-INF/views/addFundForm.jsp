<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 10.12.2020
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add fund form</title>
    <style>
        button {
            background-color: greenyellow;
        }
        div {
            vertical-align: top;
        }
    </style>

</head>
<body>

<form:form modelAttribute="fund" method="post">
    <form:hidden path="id"/>
    <p>ISIN <form:input path="ISIN" placeholder="9 digits"/></p>
    <p>Fund name <form:input path="fundName"/></p>
    <p>Client name <form:input path="clientName"/></p>
    <p>Fund currency <form:input path="currency" placeholder="3 letters"/></p>
    <p>Last valuation date <form:input path="lastValuationDate" value="${lastDate}"/></p>
    <p>Total number of shares <form:input path="totalShares"/></p>
    <p>Initial share capital <form:input path="startingCapital"/></p>
    <button type="submit">Save</button>
</form:form>

</body>
</html>
