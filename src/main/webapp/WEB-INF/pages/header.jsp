<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">--%>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap-theme.min.css">--%>
    <%--<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>--%>
</head>
<body>
<div class="container">

    <nav class="navbar navbar-default navbar-inverse" role="navigation">

        <div class="navbar-header pull-left">
            <a class="navbar-brand" href="/">TaskControl</a>
        </div>

        <div class="navbar-header col-sm-offset-4">
            <sec:authentication var="principal" property="principal"/>
            <h3><span class="label label-success">${principal.username}</span></h3>
        </div>

        <div class="navbar-header navbar-brand col-sm-offset-3">
            <a href="<c:url value="/j_spring_security_logout"/>">
                <spring:message code="header.logout"/>
            </a>
        </div>

        <div class="navbar-header pull-right">
            <form method="GET" class="navbar-form navbar-right">
                <select id="language" name="lang" onchange="submit()" class="form-control">
                    <option value="en" ${pageContext.response.locale == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru" ${pageContext.response.locale == 'ru' ? 'selected' : ''}>Русский</option>
                    <option value="ua" ${pageContext.response.locale == 'ua' ? 'selected' : ''}>Українська</option>
                </select>
            </form>
        </div>
    </nav>

</div>

</body>

</html>
