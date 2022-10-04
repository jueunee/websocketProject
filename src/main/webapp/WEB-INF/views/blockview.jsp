<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin</title>
</head>
<link rel="stylesheet" href="static/css/admin.css">
<body>
<header>
    <h1>Block Member List</h1>
</header>

<table class="member">
    <thead>
    <tr>
        <th scope="col">UserId</th>
        <th scope="col">ReportedCheck</th>
        <th scope="col">BlockUser</th>
    </tr>
    </thead>
    <tbody>
    <%--                에러나면 체크--%>
    <c:forEach var="list2" items="${list2}">
        <tr>
            <td>${list2.user_id}</td>
            <td>${list2.reportcheck}</td>
            <td>${list2.blockcheck}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>