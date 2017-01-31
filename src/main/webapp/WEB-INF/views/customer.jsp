<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Customer</title>
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css"/>">--%>
    <style>
        td.decs {
            word-break: break-all;
        }
    </style>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>

<div class="container container-fluid table-responsive">
    <c:if test="${hasProject}">
        <h1>SPRINTS:</h1>
        <table id="sprintTable" class="table table-hover table-condensed">
            <thead>
            <th>ID</th>
            <th>Name</th>
            <th>StartDate</th>
            <th>EndDate</th>
            <th>Complete?</th>
            </thead>
            <tbody>
            <c:forEach items="${sprintList}" var="item">
                <tr id="${item.id}">
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.startDate}</td>
                    <td>${item.endDate}</td>
                    <td>${item.complete}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <h1>TASKS:</h1>
        <table id="taskTable" class="table table-hover table-condensed">
            <thead>
            <th>ID</th>
            <th>Name</th>
            <th>Estimate</th>
            <th>SubTaskOf</th>
            <th>StartDate</th>
            <th>EndDate</th>
            <th>Remaining(d)</th>
            <th>Qualification</th>
            <th>Complete?</th>
            </thead>
            <tbody align="center">
            </tbody>
        </table>
    </c:if>


    <c:if test="${not hasProject}">
        <div class="col-md-4 col-md-offset-1">
            <div class="login-panel panel panel-login panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">New Proposal Registration Form</h3>
                </div>
                <div class="panel-body">
                    <form:form method="POST" modelAttribute="proposal">

                        <div class="form-group">
                            <form:input type="text" path="name" id="name" cssClass="form-control"
                                        placeholder="Project Name"/>
                            <form:errors path="name"/>
                        </div>

                        <div class="form-group">
                            <form:textarea type="text" path="description" id="description" cssClass="form-control"
                                           placeholder="Description"/>
                            <form:errors path="description"/>
                        </div>

                        <input type="submit" value="Register" class="btn btn-primary btn-lg btn-block">
                    </form:form>
                </div>
            </div>
        </div>


        <c:if test="${not empty list}">
            <div class="col-md-4 col-md-offset-2">
                <h1 align="center">Proposals:</h1>
                <table class="table table-hover table-condensed">
                    <th>Name</th>
                    <th>Description</th>
                    <c:forEach items="${list}" var="item">
                        <tr>
                            <td>${item.name}</td>
                            <td class="decs">${item.description}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </c:if>


    </c:if>
</div>


</body>
<%--<script src="<c:url value="/resources/js/http_code.jquery.com_jquery-1.10.2.js"/>"></script>--%>
<%--<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>--%>

<script>
    $(document).ready(function () {
        $("table#sprintTable").delegate("tr", "click", function (event) {
            var id = event.currentTarget.id;
            $.ajax({
                type: "POST",
                url: "/project-manager/sprint/" + id,
                success: function (response) {
                    $("#taskTable > thead").hide();
                    $("#taskTable > tbody").empty();
                    if (parseInt(response.length) > 0) {

                        $("#taskTable > thead").show();

                        for (i = 0; i < parseInt(response.length); i++) {
                            var startDate = response[i].startDate.split("-");
                            endDate = new Date().setDate(new Date(startDate[0], startDate[1], startDate[2]).getDate() + response[i].estimate);
                            var tr = '<tr ' + 'id="' + response[i].id + '"' + '>'
                                    + '<td>' + response[i].id + '</td>'
                                    + '<td>' + response[i].name + '</td>'
                                    + '<td>' + response[i].estimate + '</td>'
                                    + '<td>' + response[i].primaryTask + '</td>'
                                    + '<td>' + response[i].startDate + '</td>'
                                    + '<td>' + response[i].endDate + '</td>'
                                    + '<td>' + Math.round((new Date(endDate) - new Date()) / (1000 * 60 * 60 * 24)) + '</td>'
                                    + '<td>' + response[i].qualification + '</td>'
                                    + '<td>' + response[i].complete + '</td>'
                                    + '</tr>';
                            $("#taskTable > tbody").append(tr);
                        }
                    }
                },
            });
        });
    });
</script>

</html>
