<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accounts</title>
</head>
<body>
<table>
    <c:forEach var="account" items="${accounts}">
        <tr>
            <td>Account ID: <c:out value="${account.id}"/><td>
        </tr>
    </c:forEach>
</table>

</body>
</html>