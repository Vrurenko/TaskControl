<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <title>Admin</title>
    <style>
        .wid {
            width: 50px;
            text-align: center;
        }
    </style>
</head>
<body>

<div>
    <jsp:include page="header.jsp"/>
</div>

<div class="container container-fluid table-responsive">
    <c:if test="${not empty proposalList}">
        <table class="table table-hover table-condensed">
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>EndDate</th>
            <th>Manager</th>
            <c:forEach items="${proposalList}" var="item">
                <form:form method="POST" modelAttribute="project">
                    <tr>
                        <td>
                            <form:input path="proposalId"
                                        id="id"
                                        value="${item.id}"
                                        cssClass="wid"
                                        disabled="true"/>
                            <form:errors path="proposalId"/>
                        </td>
                        <td>
                            <form:input type="text" path="name" id="name" value="${item.name}" cssClass="form-control"/>
                            <form:errors path="name"/>
                        </td>
                        <td>
                                ${item.description}
                        </td>
                        <td>
                                <form:input path="endDate"
                                id="endDate"
                                cssClass="datepicker"
                                type="date"/>
                                <form:errors path="endDate"/>
                        </td>
                        <td>
                            <form:select path="manager"
                                         items="${employeeList}"
                                         id="manager"
                                         cssClass="form-control"/>
                            <form:errors path="manager"/>
                        </td>
                        <td>
                            <input type="submit" value="Register" class="button btn btn-primary">
                        </td>
                    </tr>
                </form:form>
            </c:forEach>
        </table>
    </c:if>
</div>


</body>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        $('[type="date"]').prop('min', function () {
            Date.prototype.addDays = function (days) {
                var date = new Date(this.valueOf());
                date.setDate(date.getDate() + days);
                return date;
            }
            return new Date().addDays(1).toJSON().split('T')[0];
        });
        $(function () {
            $('#datetimepicker2').datetimepicker({
                locale: 'ru'
            });
        });
    });
</script>
</html>
