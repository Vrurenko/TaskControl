<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>
        <spring:message code="welcome.title"/>
    </title>
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
                            <a href="/admin"><h3><spring:message code="welcome.administrator"/></h3></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_CUSTOMER')">
                            <a href="/customer"><h3><spring:message code="welcome.customer"/></h3></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_EMPLOYEE')">
                            <a href="/employee"><h3><spring:message code="welcome.employee"/></h3></a>
                        </sec:authorize>
                        <sec:authorize access="hasRole('ROLE_MANAGER')">
                            <a href="/project-manager"><h3><spring:message code="welcome.manager"/></h3></a>
                        </sec:authorize>
                        <p><spring:message code="welcome.account.description"/></p>
                    </div>
                </div>
            </div>

            <div class="col-sm-3 col-md-3">
                <div class="thumbnail">
                    <img src="<c:url value="/resources/pics/report.jpg"/>" alt="Report">
                    <div class="caption" align="center">
                        <a href="/report"><h3><spring:message code="welcome.report"/></h3></a>
                        <p><spring:message code="welcome.report.description"/></p></div>
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>
