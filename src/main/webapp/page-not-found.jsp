<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false"%>
<html>
<head>
    <title>Page not found</title>
    <style>
        img{
            margin-left: 40%;
        }
        h1{
            font-size: 3em;
        }
        a{
            align-items: center;
            font-size: 1.5em;
        }
    </style>
</head>
<body>
<div>
    <img src="<c:url value="/resources/pics/404.jpg"/>"/>
    <h1 align="center">404</h1>
    <div align="center">
        <a href="/welcome">Get back in the saddle!</a>
    </div>
</div>
</body>
</html>
