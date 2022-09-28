<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signup</title>

</head>
<body>
<div class="container">
    <h2>회원가입</h2>
    <form action="/signup1" method="post">
<%--        관리자인지 사용자인지--%>

        <p>
            <label for="user_id">아이디</label>
            <input type="text" id="user_id" name="user_id">
        </p>
        <p>
            <label for="pw">비밀번호</label>
            <input type="text" id="pw" name="pw">
        </p>
        <p>
            <label for="name">이름</label>
            <input type="text" id="name" name="name">
        </p>
        <p>
            <label for="email">이메일</label>
            <input type="text" id="email" name="email">
        </p>
        <p>
            <label for="gender">성별</label>
            <input type="text" id="gender" name="gender">
        </p>
        <p>
            <label for="mbti">MBTI</label>
            <input type="text" id="mbti" name="mbti">
        </p>
        <button type="submit">회원가입</button>
    </form>
        <p><a href="/login">로그인</a> </p>
        <p> 현재시각은 ${joinDated}.</p>

</div>
</body>
</html>