<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Manager</title>
</head>
<body>

<h1>SPRINTS:</h1>
<table id="sprintTable">
    <c:if test="${not empty sprintList}">
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
                <td>${item.complete ? "" : "<button id='close'>Close</button>"}</td>
            </tr>
        </c:forEach>
        </tbody>
    </c:if>
</table>

<button id="newSprint">+</button>

<h1>TASKS:</h1>
<table id="taskTable">
    <thead>
    </thead>
    <tbody align="center">
    </tbody>
</table>

<h1>TASK:</h1>
<table id="Task">
    <thead>
    </thead>
    <tbody align="left">
    </tbody>
</table>

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
                    $("#taskTable > thead").empty();
                    $("#taskTable > tbody").empty();
                    if (parseInt(response.length) > 0) {
                        var thead = '<tr>'
                                + '<th>ID</th>'
                                + '<th>Name</th>'
                                + '<th>Estimate</th>'
                                + '<th>SubTaskOf</th>'
                                + '<th>StartDate</th>'
                                + '<th>EndDate</th>'
                                + '<th>Remaining(d)</th>'
                                + '<th>Qualification</th>'
                                + '<th>Complete?</th>'
                                + '</tr>';
                        $("#taskTable > thead").append(thead);

                        for (i = 0; i < parseInt(response.length); i++) {
                            var date1 = response[i].startDate.split("-");
                            var date2 = response[i].endDate.split("-");
                            var tr = '<tr ' + 'id="' + response[i].id + '"' + '>'
                                    + '<td>' + response[i].id + '</td>'
                                    + '<td>' + response[i].name + '</td>'
                                    + '<td>' + response[i].estimate + '</td>'
                                    + '<td>' + response[i].subTaskOf + '</td>'
                                    + '<td>' + response[i].startDate + '</td>'
                                    + '<td>' + response[i].endDate + '</td>'
                                    + '<td>' + Math.abs(new Date(date2[0], date2[1], date2[2]) - new Date(date1[0], date1[1], date1[2])) / 864e5 + '</td>'
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
<script>
    $(document).ready(function () {
        $("table#taskTable").delegate("tr", "click", function (event) {
            var id = event.currentTarget.id;
            $.ajax({
                type: "POST",
                url: "/project-manager/task/" + id,
                success: function (response) {
                    $("#Task > thead").empty();
                    $("#Task > tbody").empty();
                    var date1 = response.startDate.split("-");
                    var date2 = response.endDate.split("-");
                    var tr = '<tr>' + '<th>ID</th>' + '<td id="' + response.id + '">' + response.id + '</td>' + '</tr>'
                            + '<tr>' + '<th>Name</th>' + '<td>' + response.name + '</td>' + '</tr>'
                            + '<tr>' + '<th>Estimate</th>' + '<td>' + response.estimate + '</td>' + '</tr>'
                            + '<tr>' + '<th>SubTaskOf</th>' + '<td>' + response.subTaskOf + '</td>' + '</tr>'
                            + '<tr>' + '<th>StartDate</th>' + '<td>' + response.startDate + '</td>' + '</tr>'
                            + '<tr>' + '<th>EndDate</th>' + '<td>' + response.endDate + '</td>' + '</tr>'
                            + '<tr>' + '<th>Remaining</th>' + '<td>' + Math.abs(new Date(date2[0], date2[1], date2[2]) - new Date(date1[0], date1[1], date1[2])) / 864e5 + '</td>' + '</tr>'
                            + '<tr>' + '<th>Qualification</th>' + '<td>' + response.qualification + '</td>' + '</tr>'
                            + '<tr>' + '<th>Complete</th>' + '<td>' + response.complete + '</td>' + '</tr>'
                            + '<tr>' + '<th>Executors</th>' + '<td id="exe"></td><td><button id="getExe">+</button></td>' + '</tr>';
                    $("#Task > tbody").append(tr);
                    var list = '';
                    $.ajax({
                        type: "POST",
                        url: "/project-manager/executors/" + id,
                        async: true,
                        success: function (answer) {
                            for (i = 0; i < parseInt(answer.length); i++) {
                                list += answer[i] + ',<br>';
                            }
                            $("#exe").append(list.slice(0, -5));
                        },
                    });
                },
            });
        });
    });
</script>
<script>
    $(document).ready(function () {
        $(document).on('click', '#getExe', function (event) {
            var position = event.currentTarget.parentElement.parentElement.childNodes[2];
            var id = event.currentTarget.parentElement.parentElement.parentElement.childNodes[0].childNodes[1].innerHTML;
            $.ajax({
                type: "POST",
                url: "/project-manager/freeexecutors/" + id,
                success: function (response) {
                    if (response.length > 0) {
                        var select = '<select>';
                        for (var i = 0; i < parseInt(response.length); i++) {
                            select += '<option>' + response[i] + '</option>';
                        }
                        select += '</select>';
                        var add = '<button id="addExe">Send</button>';
                        if (parseInt(event.currentTarget.parentElement.childNodes.length) > 1) {
                            $(event.currentTarget.parentElement.childNodes[2]).remove();
                            $(event.currentTarget.parentElement.childNodes[1]).remove();
                        }
                        $(position).append(select).append(add);
                    } else {
                        alert('Nobody to add');
                    }
                },
            });
        });
    });
</script>
<script>
    $(document).ready(function () {
        $(document).on('click', '#addExe', function (event) {
            var taskID = event.currentTarget.parentElement.parentElement.parentElement.childNodes[0].childNodes[1].innerHTML;
            var executerName = event.currentTarget.parentElement.childNodes[1].selectedOptions[0].innerHTML;
            var select = event.currentTarget.parentElement.childNodes[1];
            var send = event.currentTarget.parentElement.childNodes[2];
            var toAddList = event.currentTarget.parentElement.parentElement.childNodes[1];
            $.ajax({
                type: "POST",
                url: "/project-manager/task/" + taskID + "/emp/" + executerName,
                success: function (response) {
                    $(select).remove();
                    $(send).remove();
                    $.ajax({
                        type: "POST",
                        url: "/project-manager/executors/" + taskID,
                        success: function (response) {
                            var list = '';
                            for (var i = 0; i < response.length; i++) {
                                list += response[i] + ',<br>'
                            }
                            $(toAddList).empty().append(list.slice(0, -5));
                        },
                    });
                },
            });
        });
    });
</script>

<script>
    $(document).ready(function () {
        $("#newSprint").on("click", function () {
            $.ajax({
                type: "POST",
                url: "/project-manager/sprint/add",
                data: "name=" + "testName" + "&endDate=" + new Date().toUTCString(),
                success: function (response) {
                    alert(response);
                },
            });
        })
    });
</script>

<script>
    $(document).ready(function () {
        $(document).delegate("#close", "click", function (event) {
            $.ajax({
                type: "POST",
                url: "/project-manager/sprint/close",
                success: function (response) {
                    alert(response);
                },
            });
        });
    });
</script>

</html>





















