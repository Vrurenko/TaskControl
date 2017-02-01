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
    <table class="table table-hover table-condensed">
        <c:if test="${not empty taskDelay}">
        <thead>
        <th><spring:message code="report.taskname"/></th>
        </thead>
        <tbody>
        <c:forEach items="${taskDelay}" var="item">
            <tr ${item.value < 0 ? "class='warning'" : "class='success'"} >
                <td>${item.key}</td>
            </tr>
        </c:forEach>
        </c:if>
        </tbody>
    </table>

</div>

</body>
</html>