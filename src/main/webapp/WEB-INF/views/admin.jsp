<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org" xmlns="http://www.w3.org/1999/html">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin</title>
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
    <!-- Core theme CSS (includes Bootstrap)-->
<%--    <link href="/css/styles2.css" rel="stylesheet" />--%>
<%--    <style>--%>
<%--        .bd-placeholder-img {--%>
<%--            font-size: 1.125rem;--%>
<%--            text-anchor: middle;--%>
<%--            -webkit-user-select: none;--%>
<%--            -moz-user-select: none;--%>
<%--            user-select: none;--%>
<%--        }--%>

<%--        @media (min-width: 768px) {--%>
<%--            .bd-placeholder-img-lg {--%>
<%--                font-size: 3.5rem;--%>
<%--            }--%>
<%--        }--%>
<%--    </style>--%>
</head>
<body>
<!-- Navigation-->
<nav class="navbar navbar-expand-lg navbar-light bg-light" th:replace="/fragment/navigation :: menu(${user})">
</nav>
<div class="container-fluid mt-3">
<div class="row">

    <!-- SIDE BAR -->
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse" th:replace="/fragment/navigation :: side(${user})">
    </nav>

    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">Admin Page</h1>
        </div>
        <div class="table-responsive">
            <table class="table table-striped table-sm">
                <thead>
                <tr>
                    <th scope="col">UserId</th>
                    <th scope="col">UserRole</th>
                    <th scope="col">ReportedCheck</th>
                    <th scope="col">BlockUser</th>
                </tr>
                </thead>
                <tbody>
<%--                에러나면 체크--%>
                <tr th:each="user : ${memberList}">
                    <form th:action="/admin" method="post" class="d-flex">
                        <td th:text="${member.user_id}"></td>
                        <td th:text="${user.grade}"></td>
                        <td th:text="${user.joinDated()}"></td>
                    </form>
                </tr>
                </tbody>
            </table>
        </div>
    </main>
</div>
</div>
<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<%--<script src="/js/scripts.js"></script>--%>
</body>
</html>