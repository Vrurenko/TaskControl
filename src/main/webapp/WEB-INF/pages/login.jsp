<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        #error{
            display: none;
            color: red;
        }
    </style>
</head>
<body>
<h2>Please Login!</h2>
<form action="j_spring_security_check" method='POST'>
    Username: <input type="text" name="j_username" /><br />
    Password: <input type="password" name="j_password" />
    <div id="error">
        Invalid username and/or password!
    </div>
    <br/>
    <input type="submit" value="Login" />
    <script>
        var field = 'error';
        var url = window.location.href;
        if(url.indexOf('?' + field + '=') != -1){
            document.getElementById("error").style.display="block";
        }
    </script>
</form>
</body>
</html>
