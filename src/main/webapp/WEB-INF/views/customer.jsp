<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title><spring:message code="customer.title"/></title>
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

        <div class="col-md-4">
            <h1><spring:message code="customer.sprints.header"/></h1>
            <table id="sprintTable" class="table table-hover table-condensed">
                <thead>
                <th>ID</th>
                <th><spring:message code="customer.name"/></th>
                <th><spring:message code="customer.start"/></th>
                <th><spring:message code="customer.end"/></th>
                </thead>
                <tbody>
                <c:forEach items="${sprintList}" var="item">
                    <tr id="${item.id}" class="${item.complete ? "success" : "warning"}">
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.startDate}</td>
                        <td>${item.endDate}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="col-md-8">
            <h1><spring:message code="customer.tasks.header"/></h1>
            <table id="taskTable" class="table table-hover table-condensed">
                <thead>
                <th>ID</th>
                <th><spring:message code="customer.name"/></th>
                <th><spring:message code="customer.estimate"/></th>
                <th><spring:message code="customer.primary"/></th>
                <th><spring:message code="customer.start"/></th>
                <th><spring:message code="customer.end"/></th>
                <th><spring:message code="customer.qualification"/></th>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </c:if>


    <c:if test="${not hasProject}">
        <div class="col-md-4 col-md-offset-1">
            <div class="login-panel panel panel-login panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><spring:message code="customer.form.header"/></h3>
                </div>
                <div class="panel-body">
                    <form:form method="POST" modelAttribute="proposal">

                        <div class="form-group">
                            <spring:message code="customer.form.name.placeholder" var="i18nname"/>
                            <form:input type="text" path="name" id="name" cssClass="form-control"
                                        placeholder="${i18nname}"/>
                            <form:errors path="name"/>
                        </div>

                        <div class="form-group">
                            <spring:message code="customer.form.description.placeholder" var="i18ndesc"/>
                            <form:textarea type="text" path="description" id="description" cssClass="form-control"
                                           placeholder="${i18ndesc}"/>
                            <form:errors path="description"/>
                        </div>

                        <input type="submit" value="<spring:message code="customer.form.button.submit"/>" class="btn btn-primary btn-lg btn-block">
                    </form:form>
                </div>
            </div>
        </div>


        <c:if test="${not empty list}">
            <div class="col-md-4 col-md-offset-2">
                <h1 align="center"><spring:message code="customer.table.header"/></h1>
                <table class="table table-hover table-condensed">
                    <th><spring:message code="customer.name"/></th>
                    <th><spring:message code="customer.form.description.placeholder"/></th>
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
<script src="<c:url value="/resources/js/http_code.jquery.com_jquery-1.10.2.js"/>"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

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
                            var tr = '<tr ' + 'id="' + response[i].id + '"' + ' class="' + (response[i].complete ? "success" : "warning") + '">'
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
                }
            });
        });
    });
</script>

</html>
