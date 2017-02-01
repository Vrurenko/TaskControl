<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Manager</title>
    <style>
        .table {
            font-size: 9pt;
            font-weight: 400;
        }

        div.panel-heading {
            height: 20px;
            padding: 0;
        }
        .completed {
            background-color: rgba(98, 255, 37, 0.5);
        }

        .uncompleted {
            background-color: rgba(255, 151, 221, 0.5);
        }
        .without-margin-bottom{
            margin-bottom: 5px;
        }
        .append-margin-bottom{
            margin-bottom: 20px;
        }
    </style>
</head>
<body>


<div class="container">

    <div id="header">
        <jsp:include page="header.jsp"/>
    </div>

    <div class="col-sm-offset-7">
        <button class="button btn btn-primary" onclick="releaseProject()">Release</button>
    </div>

    <div class="col-md-12">
        <div class="col-md-8">
            <div class="panel panel-default without-margin-bottom">
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
                        </thead>
                        <tbody>
                        <c:forEach items="${sprintList}" var="item">
                            <tr id="${item.id}" class="${item.complete ? "success" : "warning"}">
                                <td>${item.id}</td>
                                <td>${item.name}</td>
                                <td>${item.startDate}</td>
                                <td>${item.endDate}</td>
                                <td>${item.complete ? "" : "<button id='close' class='button btn btn-primary'>Close</button>"}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </c:if>
                </table>
            </div>

            <div class="form-inline append-margin-bottom">
                <button id="newSprint" class='button btn btn-primary'>+</button>
            </div>



            <div class="panel panel-default without-margin-bottom">
                <div class="panel-heading">
                    <h4 class="text-center">TASKS</h4>
                </div>
                <table id="taskTable" class="table table-hover">
                    <thead>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Estimate(d)</th>
                    <th>Primary</th>
                    <th>StartDate</th>
                    <th>EndDate</th>
                    <th>Qualification</th>
                    </thead>
                    <tbody align="center">
                    </tbody>
                </table>
            </div>
            <%--<div class="form-inline">--%>
                <div class="form-group" id="first">
                    <div class="col-md-1">
                        <button id="newTask" class="button btn btn-primary">+</button>
                    </div>
                </div>
            <%--</div>--%>


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
</div>


</body>
<script src="<c:url value="/resources/js/http_code.jquery.com_jquery-1.10.2.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
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
                        tbody += '<tr id=' + response[i].id + ' class="' + (response[i].complete ? "success" : "warning") + '">'
                                + '<td>' + response[i].id + '</td>'
                                + '<td>' + response[i].name + '</td>'
                                + '<td>' + response[i].startDate + '</td>'
                                + '<td>' + (response[i].endDate || '') + '</td>'
                                + '<td>' + (response[i].complete ? '' : '<button id="close" class="button btn btn-primary">Close</button>') + '</td>'
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
                }
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
                $("#newSprint").after('<input class="form-control" type = text id="sprintName" placeholder="Project Name">');
                $("#sprintName").after('<input class="form-control" type = date id="sprintEnd">');
                $("#sprintEnd").after('<button id="sendSprint" class="button btn btn-primary">Create</button>');
                $('#sprintEnd').prop('min', tomorrow());
                $('#sprintEnd').prop('value', tomorrow());
            } else {
                for (i = 0; i < 3; i++) {
                    $("#newSprint").next().remove();
                }
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
            if ($("#first").contents().length == 3) {
                $("#newTask").closest("div").after('<div class="col-md-3"><input class="form-control " type = text id="taskName" placeholder="Task Name"></div>');
                $("#taskName").closest("div").after('<div class="col-md-2"><input class="form-control" type = number min="1" id="taskEstimate" placeholder="Days"></div>');
                $("#taskEstimate").closest("div").after('<div class="col-md-2"><select id="primary" class="form-control"></select></div>');
                $("#primary").closest("div").after('<div class="col-md-2"><select id="qualification" class="form-control"></select></div>');
                $('#qualification').closest("div").after('<div class="col-md-2"><button id="sendTask" class="button btn btn-primary">SendTask</button></div>');
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
                        $("#primary").append(options);
                    }
                });
            } else {
                for (i = 0; i < 5; i++) {
                    $("#first").contents()[2].remove();
                }
            }
        });
    });

    $(document).ready(function () {
        $(document).delegate("button#sendTask", "click", function () {
            var name = $("#taskName").val();
            var estimate = $("#taskEstimate").val();
            var primary = $("#primary").val();
            var qualification = $("#qualification").val();
            var currentSprint = $("table#sprintTable").find('tr').last();
            debugger;
            $.ajax({
                type: "POST",
                url: "/project-manager/task/add",
                data: "name=" + name
                + "&estimate=" + estimate
                + "&primaryTask=" + (primary == 'Empty' ? null : primary)
                + "&qualification=" + qualification,
                success: function (response) {
                    $("#taskName").remove();
                    $("#taskEstimate").remove();
                    $("#primary").remove();
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
                            var tr = '<tr ' + 'id="' + response[i].id + '"' + 'class="' + (response[i].complete ? "success" : "warning") + '"' + '>'
                                    + '<td>' + response[i].id + '</td>'
                                    + '<td>' + response[i].name + '</td>'
                                    + '<td>' + response[i].estimate + '</td>'
                                    + '<td>' + (response[i].primaryTask || '') + '</td>'
                                    + '<td>' + response[i].startDate + '</td>'
                                    + '<td>' + (response[i].endDate || '') + '</td>'
                                    + '<td>' + response[i].qualification + '</td>'
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
                    var tr = '<tr>' + '<th>ID</th>' + '<td id="' + response.id + '">' + response.id + '</td>' + '</tr>'
                            + '<tr>' + '<th>Name</th>' + '<td>' + response.name + '</td>' + '</tr>'
                            + '<tr>' + '<th>Estimate</th>' + '<td>' + response.estimate + '</td>' + '</tr>'
                            + '<tr>' + '<th>Primary</th>' + '<td>' + (response.primaryTask || '') + '</td>' + '</tr>'
                            + '<tr>' + '<th>StartDate</th>' + '<td>' + response.startDate + '</td>' + '</tr>'
                            + '<tr>' + '<th>EndDate</th>' + '<td>' + response.endDate + '</td>' + '</tr>'
                            + '<tr>' + '<th>Qualification</th>' + '<td>' + response.qualification + '</td>' + '</tr>'
                            + '<tr>' + '<th>Complete</th>' + '<td>' + response.complete + '</td>' + '</tr>'
                            + '<tr>' + '<th>Executors</th>' + '<td id="exe"></td>' + '</tr>'
                            + '<tr>' + '<th></th>' + '<td></td><td><button id="getExe" class="button btn btn-primary">+</button></td>' + '</tr>';
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
                        }
                    });
                }
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
                        if (!$("#addExe").length) {
                            var select = '<select>';
                            for (var i = 0; i < parseInt(response.length); i++) {
                                select += '<option>' + response[i] + '</option>';
                            }
                            select += '</select>';
                            var add = '<button id="addExe" class="button btn btn-primary">Send</button>';
                            $(position).append(select).append(add);
                        } else {
                            $("#getExe").next().remove();
                            $("#getExe").next().remove();
                        }

                    } else {
                        alert('Nobody to add');
                    }
                }
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
            var toAddList = event.currentTarget.parentNode.parentNode.parentNode.childNodes[8].childNodes[1];

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
                        }
                    });
                }
            });
        });
    });
</script>

<script>
    var releaseProject = function () {
        $.ajax({
            type: "POST",
            url: "/project-manager/project/close",
            success: function (response) {
                if (response) {
                    document.location.href = "/j_spring_security_logout";
                } else {
                    rebuildSprintList();
                }
            }
        });
    }
</script>


</html>





















