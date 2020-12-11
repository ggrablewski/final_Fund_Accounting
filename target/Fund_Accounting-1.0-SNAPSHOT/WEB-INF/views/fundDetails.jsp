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
    <title>Fund details</title>
</head>
<body>

<h4>Fund details</h4>

<table>
    <tbody>
    <tr>
        <td>ISIN </td>
        <td>${fund.ISIN}</td>
    </tr>
    <tr>
        <td>Fund name </td>
        <td>${fund.fundName}</td>
    </tr>
    <tr>
        <td>Client name </td>
        <td>${fund.clientName}</td>
    </tr>
    <tr>
        <td>Total shares </td>
        <td>${fund.totalShares}</td>
    </tr>
    <tr>
        <td>Share capital </td>
        <td>${fund.currency}&nbsp${fund.shareCapital}</td>
    </tr>
    <tr>
        <td>Last valuation date </td>
        <td>${fund.lastValuationDate}</td>
    </tr>
    <tr>
        <td>Last NAV </td>
        <td>${fund.currency}&nbsp${NAV}</td>
    </tr>
    </tbody>
</table>
<a href="/fund/list">Return to the fund list</a>

</body>
</html>
