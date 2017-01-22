<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Admin</title>
</head>
<body>
<h2>ADMIN PAGE</h2>

<table>
    <th>ID</th>
    <th>Name</th>
    <th>Description</th>
    <th>EndDate</th>
    <th>Manager</th>
    <c:forEach items="${proposalList}" var="item">
        <form:form method="POST" modelAttribute="project">
            <tr>
                <td class="dis">
                    <form:input path="proposalId" id="id" value="${item.id}"/>
                    <form:errors path="proposalId"/>
                </td>
                <td>
                    <form:input type="text" path="name" id="name" value="${item.name}"/>
                    <form:errors path="name"/>
                </td>
                <td>
                    <textarea disabled rows="3" cols="20">
                            ${item.description}
                    </textarea>
                </td>
                <td>
                    <form:input path="endDate" id="endDate" type="date"/>
                    <form:errors path="endDate"/>
                </td>
                <td>
                    <form:select path="manager" items="${employeeList}" id="manager"/>
                    <form:errors path="manager"/>
                </td>
                <td>
                    <input type="submit" value="Register">
                </td>
            </tr>
        </form:form>
    </c:forEach>
</table>
<br>
<br>
<a href="<c:url value="/j_spring_security_logout" />">Logout</a>
</body>

<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
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
        $(".dis > input").prop("disabled", true);
    });
</script>
</html>
