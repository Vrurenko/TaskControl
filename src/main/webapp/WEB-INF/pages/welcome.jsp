<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>

<a href="/admin"><h2>ADMIN</h2></a>
<a href="/customer"><h2>CUSTOMER</h2></a>
<a href="/employee"><h2>EMPLOYEE</h2></a>
<a href="/project-manager"><h2>MANAGER</h2></a>


</body>
</html>
