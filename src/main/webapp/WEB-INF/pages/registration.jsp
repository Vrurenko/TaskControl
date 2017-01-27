<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>

<body>


<h1>New User Registration Form</h1>

<form:form method="POST" modelAttribute="user">

    <label for="name">Name</label>
    <form:input type="text" path="name" id="name"/>
    <form:errors path="name"/>

    <br>

    <label for="surname">Surname</label>
    <form:input type="text" path="surname" id="surname"/>
    <form:errors path="surname"/>

    <br>

    <label for="login">Login</label>
    <form:input type="text" path="login" id="login"/>
    <form:errors path="login"/>

    <br>

    <label for="password">Password</label>
    <form:input type="password" path="password" id="password"/>
    <form:errors path="password"/>

    <br>

    <label for="role">Role</label>
    <form:select path="role" items="${roles}" id="role"/>
    <form:errors path="role"/>

    <br>

    <label for="qualification">Qualification</label>
    <form:select path="qualification" items="${qualifications}" id="qualification"/>
    <form:errors path="qualification"/>

    <br>

    <input type="submit" value="Register">

</form:form>

</body>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

<script>
    var hideQualifications = function () {
        $('label[for="qualification"]').hide();
        $("#qualification").hide();
        $("#qualification").prepend('<option value="NONE" selected>--- Select ---</option>');
    };
    var showQualifications = function () {
        $('label[for="qualification"]').show();
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