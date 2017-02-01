<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <spring:message code="registration.title"/>
    </title>
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
            <h3 class="panel-title"><spring:message code="registration.form.title"/></h3>
        </div>
        <div class="panel-body">
            <form:form method="POST" modelAttribute="user">
                <div class="form-group">
                    <spring:message code="registration.form.name.placeholder" var="i18nname"/>
                    <form:input type="text" path="name" id="name" cssClass="form-control" placeholder="${i18nname}"/>
                    <form:errors path="name" cssClass="error"/>
                </div>

                <div class="form-group">
                    <spring:message code="registration.form.surname.placeholder" var="i18nsurname"/>
                    <form:input type="text" path="surname" id="surname" cssClass="form-control" placeholder="${i18nsurname}"/>
                    <form:errors path="surname" cssClass="error"/>
                </div>

                <div class="form-group">
                    <spring:message code="registration.form.login.placeholder" var="i18nlogin"/>
                    <form:input type="text" path="login" id="login" cssClass="form-control" placeholder="${i18nlogin}"/>
                    <form:errors path="login" cssClass="error"/>
                </div>
                <div class="form-group">
                    <spring:message code="registration.form.password.placeholder" var="i18npassword"/>
                    <form:input type="password" path="password" id="password" cssClass="form-control" placeholder="${i18npassword}"/>
                    <form:errors path="password" cssClass="error"/>
                </div>
                <div class="form-group">
                    <spring:message code="registration.form.role.label" var="i18nrole"/>
                    <form:select path="role" cssClass="form-control" id="role">
                        <form:option value="" label="${i18nrole}"/>
                        <form:options items="${roles}"/>
                    </form:select>
                    <form:errors path="role" cssClass="error"/>
                </div>
                <div class="form-group">
                    <form:select path="qualification" items="${qualifications}" id="qualification" cssClass="form-control"/>
                    <form:errors path="qualification"/>
                </div>

                <input type="submit" value="<spring:message code="registration.form.submit"/>" class="btn btn-primary btn-lg btn-block">

            </form:form>
        </div>
    </div>
</div>


</body>
<script src="<c:url value="/resources/js/http_code.jquery.com_jquery-1.10.2.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
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