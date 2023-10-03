<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    <c:if test="${sessionScope.username != null}">
        <p>Welcome, ${sessionScope.username}!</p>
    </c:if>
    <a href="/logout">Logout</a>
</body>
</html>