<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="login.title"/></title>
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

<div id="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="col-md-4 col-md-offset-4">
    <div class="login-panel panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title"><spring:message code="login.form.title"/></h3>
        </div>
        <div class="panel-body">
            <form role="form" action="j_spring_security_check" method='POST'>
                    <div class="form-group" id="error">
                        <spring:message code="login.form.error"/>
                    </div>
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="<spring:message code="login.form.username.placeholder"/>" name="j_username" autofocus>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" placeholder="<spring:message code="login.form.password.placeholder"/>" name="j_password">
                    </div>
                    <div class="form-group">
                        <input type="submit" class="btn btn-lg btn-primary btn-block" value="<spring:message code="login.form.button"/>">
                    </div>
                    <a href="/registration" class="btn btn-link"><spring:message code="login.register"/></a>
            </form>
        </div>
    </div>
</div>
</body>
<script>
    var field = 'error';
    var url = window.location.href;
    if (url.indexOf('?' + field + '=') != -1) {
        document.getElementById("error").style.display = "block";
    }
</script>
</html>
