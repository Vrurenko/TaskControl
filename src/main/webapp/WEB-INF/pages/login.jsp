<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <style>
        #error {
            display: none;
            color: red;
        }

        .login-panel {
            margin-top: 25%;
        }
    </style>
</head>
<body>
<%--<h2>Please Login!</h2>--%>
<%--<form action="j_spring_security_check" method='POST'>--%>
<%--Username: <input type="text" name="j_username" /><br />--%>
<%--Password: <input type="password" name="j_password" />--%>
<%--<div id="error">--%>
<%--Invalid username and/or password!--%>
<%--</div>--%>
<%--<br/>--%>
<%--<input type="submit" value="Login" />--%>

<%--</form>--%>

<div class="col-md-4 col-md-offset-4">
    <div class="login-panel panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"> Please Sign In</h3>
        </div>
        <div class="panel-body">
            <form role="form" action="j_spring_security_check" method='POST'>
                    <div class="form-group" id="error">
                        Invalid username and/or password!
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="User Name" name="j_username" autofocus>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="Password" name="j_password">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="Login">
                    </div>
                    <a href="/registration" class="btn btn-link">Register</a>
            </form>
        </div>
    </div>
</div>
</body>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
    var field = 'error';
    var url = window.location.href;
    if (url.indexOf('?' + field + '=') != -1) {
        document.getElementById("error").style.display = "block";
    }
</script>
</html>
