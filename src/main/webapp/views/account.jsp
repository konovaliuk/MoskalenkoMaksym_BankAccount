<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Bank Account</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="h-screen flex items-center justify-center bg-[#706fd3] p-10">
        <div class="bg-[#f7f1e3] h-full w-full rounded-md">
            <div class="w-full flex flex-row border-b border-[#84817a] justify-between items-center px-6 py-3">
                <span class="text-lg font-bold">
                    Account number: <c:out value="${fn:substring(account.accountNumber, 0, 4)} ${fn:substring(account.accountNumber, 4, 8)} ${fn:substring(account.accountNumber, 8, 12)} ${fn:substring(account.accountNumber, 12, 16)}" />
                </span>
                <div class="flex flex-row gap-3 items-center">
                    <form  action="/" method="get" class="m-0">
                        <button type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer">
                            Back to accounts
                        </button>
                    </form>
                    <form  action="/sign_out" method="post" class="m-0">
                        <button type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer">
                            Sign out
                        </button>
                    </form>
                </div>
            </div>
            <div class="flex flex-col px-6">
                <div class="flex flex-row py-3">
                    <div class="flex flex-col w-1/2 gap-4">
                        <div class="flex flex-col gap-1">
                            <span class="text-xs">Balance:</span>
                            <span class="text-5xl"><c:out value="${account.balance}"/></span>
                        </div>
                        <div class="flex flex-row w-full">
                            <div class="flex flex-col w-1/2 gap-1">
                                <span class="text-xs">Account Type:</span>
                                <span class="text-xl"><c:out value="${account.type}"/></span>
                            </div>
                            <div class="flex flex-col w-1/2 gap-1">
                                <span class="text-xs">Account Status:</span>
                                <span class="text-xl"><c:out value="${account.status}"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="flex flex-col w-1/2 gap-1">
                        <div class="flex text-lg border-b border-[#84817a]">
                            <span>Account Details:</span>
                        </div>
                        <c:if test="${not empty account.openedAt}">
                            <div class="w-full flex flex-row justify-between">
                                <span>Opened At:</span>
                                <span><fmt:formatDate value="${account.openedAt}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                            </div>
                        </c:if>
                        <c:if test="${not empty account.expiredAt}">
                            <div class="w-full flex flex-row justify-between">
                                <span>Expired At:</span>
                                <span><fmt:formatDate value="${account.expiredAt}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                            </div>
                        </c:if>
                        <c:if test="${not empty account.closedAt}">
                            <div class="w-full flex flex-row justify-between">
                                <span>Closed At:</span>
                                <span><fmt:formatDate value="${account.closedAt}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                            </div>
                        </c:if>
                    </div>
                </div>
                <div class="flex flex-col">
                    <table>
                        <tr class="flex flex-row py-3 border-b border-[#84817a]">
                            <th class="w-1/4 text-left">From</th>
                            <th class="w-1/4 text-left">To</th>
                            <th class="w-1/6 text-left">Volume</th>
                            <th class="w-1/6 text-left">Type</th>
                            <th class="w-1/6 text-right">Timestamp</th>
                        </tr>
                        <c:forEach var="transaction" items="${transactions}">
                            <tr class="flex flex-row py-3 border-b border-[#84817a] items-center text-xs">
                                <td class="w-1/4 text-left">
                                    <c:out value="${transaction.sourceAccount}"/>
                                </td>
                                <td class="w-1/4 text-left">
                                    <c:out value="${transaction.destinationAccount}"/>
                                </td>
                                <td class="w-1/6 text-left">
                                    <c:out value="${transaction.volume}"/>
                                </td>
                                <td class="w-1/6 text-left">
                                    <c:out value="${transaction.type}"/>
                                </td>
                                <td class="w-1/6 text-right">
                                    <span><fmt:formatDate value="${transaction.executedAt}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
