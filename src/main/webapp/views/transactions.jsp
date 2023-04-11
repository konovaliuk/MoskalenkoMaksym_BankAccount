<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Transactions</title>
    </head>
    <body>
        <table>
            <tr>
                <td style="padding: 4px 8px;">Transaction ID</td>
                <td style="padding: 4px 8px;">From Account ID</td>
                <td style="padding: 4px 8px;">To Account ID</td>
                <td style="padding: 4px 8px;">Volume</td>
                <td style="padding: 4px 8px;">Transaction Type</td>
            </tr>
            <c:forEach var="tx" items="${transactions}">
                <tr>
                    <td style="padding: 4px 8px;"><c:out value="${tx.id}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${tx.sourceAccount}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${tx.destinationAccount}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${tx.volume}"/></td>
                    <td style="padding: 4px 8px;"><c:out value="${tx.type}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
