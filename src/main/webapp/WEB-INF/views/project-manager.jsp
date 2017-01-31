<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Manager</title>
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">--%>
    <%--<link rel="stylesheet" href="<c:url value="/resources/css/bootstrap-theme.min.css"/>">--%>
    <%--<script src="<c:url value="/resources/js/http_code.jquery.com_jquery-1.10.2.js"/>"></script>--%>
    <%--<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>--%>
</head>
<body>

<div id="header">
    <jsp:include page="header.jsp"/>
</div>
<%--<div class="container-fluid">--%>
    <div class="col-md-12">
        <div class="col-md-8">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="text-center">SPRINTS</h4>
                </div>
                <table id="sprintTable" class="table table-hover">
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
            </div>


            <button id="newSprint">+</button>



            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="text-center">TASKS</h4>
                </div>
                <table id="taskTable" class="table table-hover">
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
            </div>
            <button id="newTask">+</button>

        </div>


        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4 class="text-center">TASK</h4>
                </div>
                <table id="Task" class="table table-hover">
                    <thead>
                    </thead>
                    <tbody align="left">
                    </tbody>
                </table>
            </div>
        </div>
    </div>
<%--</div>--%>


</body>

<script>
    var rebuildSprintList = function () {
        $.ajax({
            type: "POST",
            url: "/project-manager/sprint/get/all",
            success: function (response) {
                $("table#sprintTable > thead").hide();
                $("table#sprintTable > tbody").empty();
                if (parseInt(response.length) > 0) {
                    $("table#sprintTable > thead").show();
                    var tbody = '';
                    for (i = 0; i < parseInt(response.length); i++) {
                        tbody += '<tr id=' + response[i].id + '>'
                                + '<td>' + response[i].id + '</td>'
                                + '<td>' + response[i].name + '</td>'
                                + '<td>' + response[i].startDate + '</td>'
                                + '<td>' + response[i].endDate + '</td>'
                                + '<td>' + response[i].complete + '</td>'
                                + '<td>' + (response[i].complete ? '' : '<button id="close">Close</button>') + '</td>'
                                + '</tr>';
                    }
                    $("table#sprintTable > tbody").append(tbody);
                }
            }
        });
    };

    $(document).ready(function () {
        $(document).delegate("#close", "click", function (event) {
            $.ajax({
                type: "POST",
                url: "/project-manager/sprint/close",
                success: function (response) {
                    rebuildSprintList();
                },
            });
        });
    });
</script>

<script>
    $(document).ready(function () {
        var tomorrow = function () {
            Date.prototype.addDays = function (days) {
                var date = new Date(this.valueOf());
                date.setDate(date.getDate() + days);
                return date;
            };
            return new Date().addDays(1).toJSON().split('T')[0];
        };
        $("#newSprint").on("click", function () {
            if (!$("#sprintName").length) {
                $("#newSprint").after('<input type = text id="sprintName" placeholder="Project Name">');
                $("#sprintName").after('<input type = date id="sprintEnd">');
                $("#sprintEnd").after('<button id="sendSprint">Create</button>');
                $('#sprintEnd').prop('min', tomorrow());
                $('#sprintEnd').prop('value', tomorrow());
            }
        });
    });


    $(document).ready(function () {
        $(document).delegate("button#sendSprint", "click", function () {
            var name = $("#sprintName").val();
            var endDate = new Date($("#sprintEnd").val()).toUTCString();
            $.ajax({
                type: "POST",
                url: "/project-manager/sprint/add",
                data: "name=" + name + "&endDate=" + endDate,
                success: function (response) {
                    $("#sendSprint").remove();
                    $("#sprintName").remove();
                    $("#sprintEnd").remove();
                    rebuildSprintList();
                }
            });
        })
    });
</script>

<script>

    $(document).ready(function () {
        var tomorrow = function () {
            Date.prototype.addDays = function (days) {
                var date = new Date(this.valueOf());
                date.setDate(date.getDate() + days);
                return date;
            };
            return new Date().addDays(1).toJSON().split('T')[0];
        };
        $("#newTask").on("click", function () {
            if (!$("#taskName").length) {
                $("#newTask").after('<input type = text id="taskName" placeholder="Task Name">');
                $("#taskName").after('<input type = number min="1" id="taskEstimate" placeholder="Days">');
                $("#taskEstimate").after('<select id="subTask" ></select>');
                $("#subTask").after('<select id="qualification" ></select>');
                $('#qualification').after('<button id="sendTask">SendTask</button>');
                $('#taskEnd').prop('min', tomorrow());
                $('#taskEnd').prop('value', tomorrow());
                $.ajax({
                    type: "POST",
                    url: "/project-manager/task/qualifications",
                    success: function (response) {
                        var options = '';
                        for (i = 0; i < parseInt(response.length); i++) {
                            options += '<option>' + response[i] + '</option>';
                        }
                        $("#qualification").append(options);
                    }
                });
                $.ajax({
                    type: "POST",
                    url: "/project-manager/task/names",
                    success: function (response) {
                        var options = '<option>Empty</option>';
                        for (i = 0; i < parseInt(response.length); i++) {
                            options += '<option>' + response[i] + '</option>';
                        }
                        $("#subTask").append(options);
                    }
                });
            }
        });
    });

    $(document).ready(function () {
        $(document).delegate("button#sendTask", "click", function () {
            var name = $("#taskName").val();
            var estimate = $("#taskEstimate").val();
            var subTask = $("#subTask").val();
            var qualification = $("#qualification").val();
            var currentSprint = $("table#sprintTable").find('tr').last();
            debugger;
            $.ajax({
                type: "POST",
                url: "/project-manager/task/add",
                data: "name=" + name
                + "&estimate=" + estimate
                + "&primaryTask=" + (subTask == 'Empty' ? null : subTask)
                + "&qualification=" + qualification,
                success: function (response) {
                    $("#taskName").remove();
                    $("#taskEstimate").remove();
                    $("#subTask").remove();
                    $("#qualification").remove();
                    $("#sendTask").remove();
                    currentSprint.click();
                }
            });
        })
    });

</script>


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
//                    var date1 = response.startDate.split("-");
//                    var date2 = response.endDate.split("-");
                    var tr = '<tr>' + '<th>ID</th>' + '<td id="' + response.id + '">' + response.id + '</td>' + '</tr>'
                            + '<tr>' + '<th>Name</th>' + '<td>' + response.name + '</td>' + '</tr>'
                            + '<tr>' + '<th>Estimate</th>' + '<td>' + response.estimate + '</td>' + '</tr>'
                            + '<tr>' + '<th>SubTaskOf</th>' + '<td>' + response.primaryTask + '</td>' + '</tr>'
                            + '<tr>' + '<th>StartDate</th>' + '<td>' + response.startDate + '</td>' + '</tr>'
                            + '<tr>' + '<th>EndDate</th>' + '<td>' + response.endDate + '</td>' + '</tr>'
//                            + '<tr>' + '<th>Remaining</th>' + '<td>' + Math.abs(new Date(date2[0], date2[1], date2[2]) - new Date(date1[0], date1[1], date1[2])) / 864e5 + '</td>' + '</tr>'
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


</html>





















