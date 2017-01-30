<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access is denied</title>
    <style>
        img{
            margin-top: 10%;
        }
        a{
            align-items: center;
            font-size: 1.5em;
        }
    </style>
</head>
<body>
<div align="center">
    <img src="<c:url value="/resources/pics/403.jpg"/>">
    <div align="center">
        <a href="/welcome">Get back!</a>
    </div>
</div>
</body>
</html>
