<!DOCTYPE html>

<html lang="en" >
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<h1>Login</h1>
<form action="/login" method='POST'>
    <table>
        <tr>
            <td>Id:</td>
            <td><input type='text' name='user_id' id="user_id"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='pw' name='pw' id="pw" /></td>
        </tr>

        <tr>
            <td><input name="submit" type="submit" value="submit" />Login</td>
            <p><a href="/signupPage">Sign Up</a> </p>
        </tr>
    </table>
</form>


</body>
</html>

