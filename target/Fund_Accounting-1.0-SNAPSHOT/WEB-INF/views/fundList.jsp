<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 11.12.2020
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fund list</title>
</head>
<body>

<h4>Fund list</h4>

<table>
    <thead>
    <tr>
        <th>ISIN</th>
        <th>Fund name</th>
        <th>Last valuation date</th>
        <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${fundList}" var="fund" varStatus="index">
    <tr>
        <td>${index.count}</td>
        <td>${fund.ISIN}</td>
        <td>${fund.fundName}</td>
        <td>${fund.lastValuationDate}</td>
        <td>
            <a href="/fund/details/${fund.id}">Fund details</a>
        </td>
        </c:forEach>
    </tbody>
</table>
<br/>

<a href="/menu">Return to main menu</a>

</body>
</html>
