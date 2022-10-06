<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
        <h1>Admin Page</h1>
        <a href="/logout">로그아웃</a>
    </header>
    <c:choose>
    <c:when test="${not empty member}">
    <c:if test="${member.user_id eq 'admin'}">
    <nav>
        <ul class="nav-container">
            <li class="nav-item"><a href="/blockview">차단리스트</a></li>
        </ul>
    </nav>
    <table class="member">
        <caption>Member Information</caption>
        <thead>
        <tr>
            <th scope="col">UserId</th>
            <th scope="col">UserGrade</th>
            <th scope="col">JoinDated</th>
            <th scope="col">ReportedCheck</th>
            <th scope="col">BlockUser</th>
            <th scope="col">UpdateBlockUser</th>
            <th scope="col">Update</th>
        </tr>
        </thead>
        <tbody>
        <%--                에러나면 체크--%>
        <c:forEach var="list" items="${list}">
            <tr>
                <form action="user/change?id=${list.user_id}" method="post">
                    <td>${list.user_id}</td>
                    <td>${list.grade}</td>
                    <td>${list.joinDated}</td>
                    <td>${list.reportcheck}</td>
                    <td>${list.blockcheck}</td>
                    <td>
                        <select name="blockcheck">
                            <option value="Y">Y</option>
                            <option value="N">N</option>
                        </select>
                    </td>
                    <td>
                        <button onclick="javascript:btn()" id="update">Update</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
    </c:when>
    </c:choose>
    <script>
        function btn(){
            alert('수정 완료');
        }
    </script>
</body>

</html>