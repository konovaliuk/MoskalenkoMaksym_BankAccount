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
            <tr>
                <td style="padding: 4px 8px;">Account ID</td>
                <td style="padding: 4px 8px;">Profile ID</td>
                <td style="padding: 4px 8px;">Type</td>
                <td style="padding: 4px 8px;">Balance</td>
            </tr>
            <c:forEach var="account" items="${accounts}">
                <tr>
                    <td style="padding: 4px 8px;"><c:out value="${account.id}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${account.profileId}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${account.type}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${account.balance}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
