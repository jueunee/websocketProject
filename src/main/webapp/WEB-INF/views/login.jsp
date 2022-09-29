<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
</head>

<body>
<h1>Login</h1>
<form action="/login" method='POST'>
    <table>
        <tr>
            <td>Id:</td>
            <td><input type='text' name='user_id' id="user_id"></td>
        </tr>
        <div class="check_font" id="id_check"></div>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='pw' id="pw" /></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="submit" /></td>
<%--            <td><button id="logoutBtn" type="submit">logout</button></td>--%>
            <p><a href="/signupPage">Sign Up</a> </p>
        </tr>
    </table>
</form>
<script>
    $(document).ready(function(){
        $("#logoutBtn").on("click", function(){
            location.href="/login";
        })

    })
    // $("#user_id").blur(function (){
    //     var user_id=$('user_id').val();
    //     $.ajax({
    //         url : '/idcheck',
    //         type : 'get',
    //         success : function (data){
    //             console.log("1 = 중복o / 0 = 중복x : "+ data);
    //
    //             if(data == 1){
    //                 $("id_check").text("사용중인 아이디입니다.");
    //                 $("id_check").css("color", "red");
    //                 $("id_check").attr("disabled",true);
    //             } else {
    //
    //                 if (idJ.test(user_id)){
    //                     $("id_check").text("");
    //                     $("#reg_submit").attr("disabled",false);
    //
    //                 } else if(user_id==""){
    //                     $("id_check").text("아이디를 입력해주세요.");
    //                     $("id_check").css("color", "red");
    //                     $("id_check").attr("disabled",true);
    //
    //                 } else{
    //                     $("id_check").text("아이디는 소문자와 숫자 4~12자리 가능합니다.");
    //                     $("id_check").css("color", "red");
    //                     $("id_check").attr("disabled",true);
    //
    //                 }
    //
    //             }
    //         }, error : function () {
    //             console.log("실패");
    //         }
    //     });
    // });
</script>
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
</body>
</html>

