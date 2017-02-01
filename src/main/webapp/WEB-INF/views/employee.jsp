<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><spring:message code="employee.title"/></title>
</head>
<body>
<div>
    <jsp:include page="header.jsp"/>
</div>
<div class="container container-fluid table-responsive">
    <h1><spring:message code="employee.tasklist"/></h1>
    <table id="tasks" class="table table-hover table-condensed">
        <c:if test="${not empty taskList}">
        <thead>
        <th>ID</th>
        <th><spring:message code="customer.name"/></th>
        <th><spring:message code="employee.sprint"/></th>
        <th><spring:message code="customer.primary"/></th>
        <th><spring:message code="customer.estimate"/></th>
        <th><spring:message code="customer.start"/></th>
        <th><spring:message code="customer.end"/></th>
        <th colspan="2"></th>
        </thead>
        <tbody>
        <c:forEach items="${taskList}" var="item">
            <tr id="${item.id}">
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.sprint}</td>
                <td>${item.primaryTask}</td>
                <td>${item.estimate}</td>
                <td>${item.startDate}</td>
                <td>${item.endDate}</td>
                <spring:message code="employee.button.confirm" var="i18nconfirm"/>
                <td>${item.confirm ? '' : '<button class="button btn btn-default">'.concat(i18nconfirm).concat('</button>')}</td>
                <spring:message code="employee.button.complete" var="i18ncomplete"/>
                <td>${item.confirm ? '<button class="button btn btn-primary">'.concat(i18ncomplete).concat('</button>') : ''}</td>
            </tr>
        </c:forEach>
        </c:if>

        </tbody>
    </table>

</div>

</body>
<script src="<c:url value="/resources/js/http_code.jquery.com_jquery-1.10.2.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
<script>
    $(document).ready(function () {
        $("table#tasks").delegate('.button', 'click', function (event) {
            var notConfirmed = event.currentTarget.parentNode.parentNode.childNodes[19].childNodes.length;
            var taskID = event.currentTarget.parentNode.parentNode.childNodes[1].childNodes[0].nodeValue;
            var head = event.currentTarget.parentNode.parentNode.parentNode.parentNode.childNodes[1].innerHTML;
            var rebuild = function () {
                $.ajax({
                    type: "POST",
                    url: "/employee/tasks",
                    success: function (response) {

                        $("#tasks > thead").empty();
                        $("#tasks > tbody").empty();

                        if (parseInt(response.length) > 0) {
                            $("#tasks > thead").append(head);
                            for (i = 0; i < parseInt(response.length); i++) {
                                var tr = '<tr ' + 'id="' + response[i].id + '"' + '>\n'
                                        + '<td>' + response[i].id + '</td>\n'
                                        + '<td>' + response[i].name + '</td>\n'
                                        + '<td>' + response[i].sprint + '</td>\n'
                                        + '<td>' + (response[i].primaryTask || '') + '</td>\n'
                                        + '<td>' + response[i].estimate + '</td>\n'
                                        + '<td>' + response[i].startDate + '</td>\n'
                                        + '<td>' + (response[i].endDate || '') + '</td>\n'
                                        + '<td>' + '' + '</td>\n'
                                        + '<td>' + response[i].confirm + '</td>\n'
                                        + '<td>' + (response[i].confirm ? '' : '<button class="button btn btn-default" >Confirm</button>') + '</td>\n'
                                        + '<td>' + (response[i].confirm ? '<button class="button btn btn-primary">Complete</button>' : '') + '</td>\n'
                                        + '</tr>';
                                $("#tasks > tbody").append(tr);
                            }
                        }
                    }
                });
            };

            if (notConfirmed == 0) {
                $.ajax({
                    type: "POST",
                    url: "/employee/complete/" + taskID,
                    success: function (response) {
                    }
                }).done(rebuild);
            } else {
                $.ajax({
                    type: "POST",
                    url: "/employee/confirm/" + taskID,
                    success: function (response) {
                    }
                }).done(rebuild);
            }
        });
    });
</script>

</html>
