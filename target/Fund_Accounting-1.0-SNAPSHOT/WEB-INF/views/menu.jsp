<%--
  Created by IntelliJ IDEA.
  User: grzegorz
  Date: 10.12.2020
  Time: 01:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main menu</title>
</head>
<body>

<h2>Main menu</h2>

<ul>

    <li><h5>Funds</h5></li>
    <ul>
        <li>
            <a href="/fund/add">
                <span>Add fund</span>
            </a>
        </li>
        <li>
            <a href="/fund/list">
                <span>Fund list</span>
            </a>
        </li>

    </ul>

    <li><h5>Securities</h5></li>
    <ul>
        <li>
            <a href="/security/add">
                <span>Add security</span>
            </a>
        </li>
        <li>
            <a href="/security/list">
                <span>Security list</span>
            </a>
        </li>

    </ul>

    <li><h5>Trades</h5></li>
    <ul>
        <li>
            <a href="/trade/add">
                <span>Add trade</span>
            </a>
        </li>
        <li>
            <a href="/trade/list">
                <span>Trade list</span>
            </a>
        </li>

    </ul>


</ul>

</body>
</html>
