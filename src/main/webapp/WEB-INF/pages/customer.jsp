<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Customer</title>
</head>
<body>

<c:if test="${hasProject}">
    <h1>SPRINTS:</h1>
    <table id="sprintTable">
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
    <table id="taskTable">
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
    <h1>New Proposal Registration Form</h1>
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


    <c:if test="${not empty list}">
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
    </c:if>

</c:if>


<br>
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>
</body>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>

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
                                    + '<td>' + response[i].subTaskOf + '</td>'
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
