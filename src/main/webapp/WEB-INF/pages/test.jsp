<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>

<html>
<head>
    <title>bookmaker.title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>

<a href="?lang=en">en</a>
<a href="?lang=ru">ru</a>
<a href="?lang=ua">ua</a>


<p><spring:message code="header.logout"/></p>
<p>${pageContext.response.locale}</p>


<%--<div class="container">--%>
<%--<nav class="navbar navbar-default navbar-inverse" role="navigation">--%>

<%--<div class="navbar-header pull-left">--%>
<%--<a class="navbar-brand" href="/">BETS</a>--%>
<%--</div>--%>
<%--<div class="navbar-header navbar-brand col-sm-offset-4">--%>
<%--</div>--%>

<%--<div class="navbar-header pull-right">--%>
<%--<form method="GET" class="navbar-form navbar-right  ">--%>
<%--<select id="language" name="language" onchange="submit()" class="form-control">--%>
<%--<option value="en" selected>English</option>--%>
<%--<option value="ru">Русский</option>--%>
<%--<option value="uk">Українська</option>--%>
<%--</select>--%>
<%--</form>--%>
<%--</div>--%>
<%--</nav>--%>

<%--<div>--%>
<%--<div class="col-md-3">--%>
<%--<ul class="nav nav-pills">--%>
<%--<li class="active">--%>
<%--<a>--%>
<%--${sessionScope.user} (${sessionScope.usertype})--%>
<%--</a>--%>
<%--</li>--%>
<%--</ul>--%>
<%--</div>--%>
<%--<div class="col-md-offset-8 col-md-1">--%>
<%--<form name="logOutForm" method="POST" action="Controller">--%>
<%--<input type="hidden" name="command" value="logOut"/>--%>
<%--<input type="submit" value="bookmaker.logout" class="btn btn-danger">--%>
<%--</form>--%>
<%--</div>--%>
<%--</div>--%>
<%--<div class="col-md-12">--%>
<%--<br>--%>
<%--<br>--%>
<%--<div class="col-md-6">--%>
<%--<br>--%>
<%--<form name="setMultiplierForm" method="POST" action="Controller" class="form-horizontal" role="form">--%>
<%--<input type="hidden" name="command" value="setMultiplier"/>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-3" for="id">bookmaker.race</label>--%>
<%--<div class="col-sm-9">--%>
<%--<input type="text" name="id" id="id" required pattern="^[0-9]+$" autocomplete="off"--%>
<%--class="form-control">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<label class="control-label col-sm-3" for="multiplier">bookmaker.multiplier</label>--%>
<%--<div class="col-sm-9">--%>
<%--<input type="text" name="multiplier" id="multiplier" required pattern="\d+(\.\d{0-2})?"--%>
<%--autocomplete="off" class="form-control">--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="form-group">--%>
<%--<div class="col-sm-offset-2 col-sm-10">--%>
<%--<button type="submit" class="btn btn-success pull-right">bookmaker.announce</button>--%>
<%--</div>--%>
<%--</div>--%>

<%--</form>--%>
<%--</div>--%>
<%--<div class="col-md-6">--%>

<%--<div class="panel panel-default">--%>
<%--<div class="panel-heading"><h4 class="text-center">bookmaker.raceslist</h4></div>--%>
<%--<table class="table table-hover">--%>
<%--<th>ID</th>--%>
<%--<th>bookmaker.table.bookmaker</th>--%>
<%--<th>bookmaker.table.bookmaker</th>--%>
<%--<th>bookmaker.table.admin</th>--%>
<%--<th>bookmaker.table.admin</th>--%>
<%--</table>--%>
<%--</div>--%>

<%--</div>--%>
<%--</div>--%>


</body>
</html>
