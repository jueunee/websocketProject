<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Signup</title>

</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<div class="container">
    <h2>회원가입</h2>
    <form action="/signup1" autocomplete="off" method="post">
        <%--        관리자인지 사용자인지--%>
        <p>
            <label for="user_id">아이디</label>
            <input type="text" id="user_id" name="user_id" placeholder="4-15자리 입력하시오" minlength="4" maxlength="15" required autofocus>
            <button id="overlappedID" type="button">중복확인</button>
            <span id="olmessage"></span><br>
        </p>
        <p>
            <label for="pw">비밀번호</label>
            <input type="password" id="pw" name="pw" required><br>
        </p>
        <p>
            <label >성별</label>
            <input type="checkbox" name="gender" value="f" >여성
            <input type="checkbox" name="gender" value="m" >남성

        </p>
        <p>
            <label for="mbti">MBTI</label>
            <input type="text" id="mbti" name="mbti" required><br>
        </p>
        <button type="submit">회원가입</button>
    </form>
        <p><a href="/login">로그인</a> </p>
<%--        <p> 현재시각은 ${joinDated}.</p>--%>

</div>
cs
<script>
    // 아이디중복확인
    $("#overlappedID").click(function (){

       $("#signup").attr("type","button");
       const id =$("#user_id").val();
        let test = {"id" : id};
        console.log(id)
        $.ajax({
            url : "/idCheck",
            type : "get",
            async: false,
            data: {"id": id},
            success : function (data){
                // console.log("1 = 중복o / 0 = 중복x : "+ data);
                if(data == 1){
                    $("#olmessage").text("사용중인 아이디입니다.");
                    $("#olmessage").css("color","red");

                } else {
                    $("#olmessage").text("사용 가능한 ID 입니다.");
                    $("#olmessage").css("color","blue");
                    $("#signup").attr("type", "submit");

                }
            }
        });
    });
</script>
</body>
</html>