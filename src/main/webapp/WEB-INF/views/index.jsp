<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <title>login success</title>
</head>
<body>
<form action="/index" method='POST'>
    <h1>Main Page</h1>
    <h2>Hello!</h2>
<%--    <%--%>
<%--        String id = (String)session.getAttribute("user_id");//꺼내올때는 다운캐스팅을 해서 꺼냄--%>
<%--        if(id == null){--%>
<%--            response.sendRedirect("login.jsp");--%>
<%--        }--%>
<%--    %>--%>
    <ul>
        <li>
            <c:if test="${member!=null}">
                <p>${member.user_id}님 환영합니다.</p>
            </c:if>
        </li>
    </ul>
    <a href="/logout">로그아웃</a>
</form>
</body>
</html>