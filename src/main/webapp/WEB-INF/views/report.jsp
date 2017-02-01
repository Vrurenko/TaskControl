<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Report</title>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>
<div align="center">
    <h3><a href="/downloadExcel">Download Excel Document</a></h3>
</div>

<div class="container container-fluid table-responsive">
    <table class="table table-hover table-condensed">
        <c:if test="${not empty taskDelay}">
        <thead>
        <th>Task</th>
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