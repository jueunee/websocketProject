<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:400,700">
    <title>Signup</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        body {
            color: #fff;
            background: #3598dc;
            font-family: 'Roboto', sans-serif;
        }
        .form-control {
            height: 41px;
            background: #f2f2f2;
            box-shadow: none !important;
            border: none;
        }
        .form-control:focus {
            background: #e2e2e2;
        }
        .form-control, .btn {
            border-radius: 3px;
        }
        .signup-form {
            width: 400px;
            margin: 30px auto;
        }
        .signup-form form {
            color: #999;
            border-radius: 3px;
            margin-bottom: 15px;
            background: #fff;
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            padding: 30px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .signup-form h2  {
            color: #333;
            font-weight: bold;
            margin-top: 0;
        }
        .signup-form hr  {
            margin: 0 -30px 20px;
        }
        .signup-form .form-group {
            margin-bottom: 20px;
        }
        .signup-form input[type="checkbox"] {
            margin-top: 3px;
        }
        .signup-form .row div:first-child {
            padding-right: 10px;
        }
        .signup-form .row div:last-child {
            padding-left: 10px;
        }
        .signup-form .btn {
            font-size: 16px;
            font-weight: bold;
            background: #3598dc;
            border: none;
            min-width: 140px;
        }
        .signup-form .btn:hover, .signup-form .btn:focus {
            background: #2389cd !important;
            outline: none;
        }
        .signup-form a {
            color: #fff;
            text-decoration: underline;
        }
        .signup-form a:hover {
            text-decoration: none;
        }
        .signup-form form a {
            color: #3598dc;
            text-decoration: none;
        }
        .signup-form form a:hover {
            text-decoration: underline;
        }
        .signup-form .hint-text  {
            padding-bottom: 15px;
            text-align: center;
        }
    </style>
    <script src="static/js/join_check.js"></script>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<div class="signup-form">
    <form action="/signup1" autocomplete="off" method="post">
    <h2>회원가입</h2>
        <p>Please fill in this form to create an account!</p>
        <hr>
        <div class="form-group">
            <input type="text" class="form-control" id="user_id" name="user_id" placeholder="4-15자리 입력하시오" minlength="4" maxlength="15" required autofocus>
            <button id="overlappedID" type="button">중복확인</button>
            <span id="olmessage"></span><br>
        </div>
        <div class="form-group">
            <input type="password" id="pw" name="pw" placeholder="Password" required><br>
        </div>
        <div class="form-group">
            <input type="radio" name="gender" value="f" required>여성
            <input type="radio" name="gender" value="m" required>남성
        </div>
        <div class="form-group">
            <td>
                <label>MBTI</label>
                <select name="mbti" required>
                    <option value="ESTP">ESTP</option>
                    <option value="ESTJ">ESTJ</option>
                    <option value="ENTP">ENTP</option>
                    <option value="ENTJ">ENTJ</option>
                    <option value="ENFP">ENFP</option>
                    <option value="ENFJ">ENFJ</option>
                    <option value="ESFJ">ESFJ</option>
                    <option value="ESFP">ESFP</option>
                    <option value="ISTP">ISTP</option>
                    <option value="ISTJ">ISTJ</option>
                    <option value="INTP">INTP</option>
                    <option value="INTJ">INTJ</option>
                    <option value="INFP">INFP</option>
                    <option value="INFJ">INFJ</option>
                    <option value="ISFJ">ISFJ</option>
                    <option value="ISFP">ISFP</option>
                </select>
            </td>
<%--            <input type="text" id="mbti" name="mbti" placeholder="MBTI" required><br>--%>
        </div>
        <div class="form-group">
            <button type="submit" id="join" onclick="javascript:btn()" class="btn btn-primary btn-lg">회원가입</button>
        </div>
        <div class="hint-text">Already have an account? <a href="/login">Login here</a></div>
    </form>

</div>

<script>
    // function btn(){
    //     alert('회원가입 완료되었습니다.');
    // }
    // 아이디중복확인
    $("#overlappedID").click(function (){
       const id =$("#user_id").val();
        let test = {"id" : id};
        console.log(id)
        if(id==""){
            alert("아이디에는 공백이 들어갈수 없습니다.")
        }else{
            if (id.trim().length!=0){
                $.ajax({
                    url : "/idCheck",
                    type : "get",
                    async: false,
                    data: {"id": id},
                    success : function (data){
                        if(data == 1){
                            $("#olmessage").text("사용중인 아이디입니다.");
                            $("#olmessage").css("color","red");
                            $("#join").attr("disabled", "disabled");
                        } else {
                            $("#olmessage").text("사용 가능한 ID 입니다.");
                            $("#olmessage").css("color","blue");
                            $("#join").removeAttr("disabled");
                        }
                    }
                });
            }
        }
    });
</script>
</body>
</html>