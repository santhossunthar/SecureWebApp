<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
</head>
<body>
    <h1>User Profile</h1>
    <c:if test="${sessionScope.username != null}">
        <p>Welcome, ${sessionScope.username}!</p>
    </c:if>
    <a href="logoutServlet">Logout</a>
</body>
</html>