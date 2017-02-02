<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title><spring:message code="report.title"/></title>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>
<div align="center">
    <h3><a href="/downloadExcel"><spring:message code="report.download"/></a></h3>
</div>

<div class="container container-fluid table-responsive">
    <div class="col-md-4">
        <h4 align="center"><spring:message code="report.timing.header"/></h4>
        <table class="table table-hover table-condensed">
            <c:if test="${not empty taskDelay}">
            <thead>
            <th><spring:message code="report.taskname"/></th>
            <th><spring:message code="report.timing"/></th>
            </thead>
            <tbody>
            <c:forEach items="${taskDelay}" var="item">
                <tr ${item.value < 0 ? "class='warning'" : "class='success'"} >
                    <td>${item.key}</td>
                    <spring:message code="report.timing.status" var="i18ntiming"/>
                    <td>${item.value < 0 ? i18ntiming : ""}</td>
                </tr>
            </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="col-md-7 col-mf-offset-1">
        <h4 align="center"><spring:message code="report.employees.header"/></h4>
        <table class="table table-hover table-condensed">
            <c:if test="${not empty userTaskInfo}">
            <thead>
            <th><spring:message code="report.username"/></th>
            <th><spring:message code="report.project"/></th>
            <th><spring:message code="report.sprint"/></th>
            <th><spring:message code="report.task"/></th>
            </thead>
            <tbody>
            <c:forEach items="${userTaskInfo}" var="item">
                <tr ${item.completed ? "class='success'" : "class='warning'"} >
                    <td>${item.userLogin}</td>
                    <td>${item.project}</td>
                    <td>${item.sprint}</td>
                    <td>${item.task}</td>
                </tr>
            </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>


</div>

</body>
</html>