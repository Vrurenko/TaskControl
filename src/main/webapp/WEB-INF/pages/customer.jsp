<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>
<h1>New Proposal Registration Form</h1>

<h3>${added}</h3>

<form:form method="POST" modelAttribute="proposal">

    <label for="name">Name</label>
    <form:input type="text" path="name" id="name"/>
    <form:errors path="name"/>

    <br>

    <label for="description">Description</label>
    <form:textarea type="text" path="description" id="description"/>
    <form:errors path="description"/>

    <input type="submit" value="Register">
</form:form>

<br>
<br>

<table>
    <th>Name</th>
    <th>Description</th>
    <c:forEach items="${list}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${item.description}</td>
        </tr>
    </c:forEach>

</table>

<br>
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>
</body>
</html>
