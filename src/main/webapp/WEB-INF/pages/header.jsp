<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<div style="width: 100%; height: 100px; background-color: #eeeeee; text-align: center;">
    <h2>Spring 3 MVC & Apache Tiles 2</h2>
    <sec:authentication var="principal" property="principal" />
    <h3>You have been loged in as ${principal.username}</h3>
    <h3>Привет мир</h3>

</div>
</body>
</html>
