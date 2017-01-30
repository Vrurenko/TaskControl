<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <style>
        .panel-login{
            margin-top: 11%;
        }
        .error{
            color: red;
        }
    </style>
</head>

<body>
<div id="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="col-md-4 col-md-offset-4">
    <div class="login-panel panel panel-login panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Register New User</h3>
        </div>
        <div class="panel-body">
            <form:form method="POST" modelAttribute="user">
                <div class="form-group">
                    <form:input type="text" path="name" id="name" cssClass="form-control" placeholder="Name"/>
                    <form:errors path="name" cssClass="error"/>
                </div>

                <div class="form-group">
                    <form:input type="text" path="surname" id="surname" cssClass="form-control" placeholder="Surname"/>
                    <form:errors path="surname" cssClass="error"/>
                </div>

                <div class="form-group">
                    <form:input type="text" path="login" id="login" cssClass="form-control" placeholder="Login"/>
                    <form:errors path="login" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:input type="password" path="password" id="password" cssClass="form-control" placeholder="Password"/>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:select path="role" cssClass="form-control" id="role">
                        <form:option value="" label="Select Role..."/>
                        <form:options items="${roles}"/>
                    </form:select>
                    <form:errors path="role" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:select path="qualification" items="${qualifications}" id="qualification" placeholder="Qualification"
                                 cssClass="form-control"/>
                    <form:errors path="qualification"/>
                </div>

                <input type="submit" value="Register" class="btn btn-primary btn-lg btn-block">

            </form:form>
        </div>
    </div>
</div>


</body>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<script>
    var hideQualifications = function () {
        $("#qualification").prepend('<option value="NONE" selected>--- Select ---</option>');
        $("#qualification").hide();

    };
    var showQualifications = function () {
        $("#qualification").show();
        $("#qualification option[value='NONE']").remove();
    }

    $(document).ready(function () {
        hideQualifications();
        $("#role").change(function () {
            if ($(this).val() === 'ROLE_EMPLOYEE') {
                showQualifications();
            } else {
                hideQualifications();
            }
        })
    });
</script>

</html>