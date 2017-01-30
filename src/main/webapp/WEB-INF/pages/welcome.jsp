<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Welcome</title>
    <style>
        img{
            height: 400px;
            width: 300px;
        }
    </style>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="container">

    <div>
        <div class="row">

            <div class="col-sm-3 col-md-3 col-md-offset-3">
                <div class="thumbnail">
                    <img src="<c:url value="/resources/pics/account.jpg"/>" alt="Account">
                    <div class="caption" align="center">
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <a href="/admin"><h3>Administrator</h3></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                            <a href="/customer"><h3>Customer</h3></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                            <a href="/employee"><h3>Employee</h3></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MANAGER')">
                            <a href="/project-manager"><h3>Manager</h3></a>
                        </sec:authorize>
                        <p>Your account page</p>
                    </div>
                </div>
            </div>

            <div class="col-sm-3 col-md-3">
                <div class="thumbnail">
                    <img src="<c:url value="/resources/pics/report.jpg"/>" alt="Report">
                    <div class="caption" align="center">
                        <a href="/excel/load"><h3>Report</h3></a>
                        <p>Report page</p></div>
                </div>
            </div>

        </div>
    </div>
</div>




</body>
</html>
