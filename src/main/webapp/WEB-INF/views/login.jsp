<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"  >
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- CSS only -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        .login-form {
            width: 340px;
            margin: 50px auto;
            font-size: 15px;
        }
        .login-form form {
            margin-bottom: 15px;
            background: #f7f7f7;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
        }
        .login-form h2 {
            margin: 0 0 15px;
        }
        .form-control, .btn {
            min-height: 38px;
            border-radius: 2px;
        }
        .btn {
            font-size: 15px;
            font-weight: bold;
        }
    </style>
</head>

<body>
<script type="text/javascript">
    var msg = "<c:out value="${msg}" />";
    if(msg != ""){
        alert(msg);
    }
</script>
<div class="login-form">
    <form action="/login" method="post">
        <h2 class="text-center">Log in</h2>
        <div class="form-group">
            <input type="text" name='user_id' id="user_id" class="form-control" placeholder="Username" required="required">
        </div>
        <div class="form-group">
            <input type="password"  name='pw' id="pw" class="form-control" placeholder="Password" required="required">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block">Log in</button>
        </div>
        <c:if test="${msg == 'failure'}">
            <div style="color: red">
                아이디 비밀번호가 틀렸습니다.
            </div>
        </c:if>
        <c:if test="${msg == 'block'}">
            <div style="color: red">
                정지된 회원입니다.
            </div>
        </c:if>
        <div class="clearfix">

            <a href="/signupPage" class="float-right">Create an Account</a>
            <a href="/adminlogin" class="float-left">Admin Login</a>
        </div>
    </form>
</div>

</body>
</html>

