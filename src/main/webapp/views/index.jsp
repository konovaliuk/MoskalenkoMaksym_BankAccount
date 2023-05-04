<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
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
                    Current user: <c:out value="${login}"/>
                </span>
                <div class="flex flex-row gap-3 items-center">
                    <c:if test="${profile_role.equals('admin') || profile_role.equals('superadmin')}">
                        <form action="/accounts_to_approve" method="get" class="m-0">
                            <button type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer">
                                Accounts to approve
                            </button>
                        </form>
                    </c:if>
                    <form action="/create_account" method="get" class="m-0">
                        <button type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer">
                            Create account
                        </button>
                    </form>
                    <form  action="/sign_out" method="post" class="m-0">
                        <button type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer">
                            Sign out
                        </button>
                    </form>
                </div>
            </div>
            <div class="flex flex-col">
                <table>
                    <tr class="flex flex-row px-6 py-3 border-b border-[#84817a]">
                        <th class="w-1/2 text-left">Account number</th>
                        <th class="w-1/6 text-right">Balance</th>
                        <th class="w-1/6 text-right">Type</th>
                        <th class="w-1/6 text-right">Actions</th>
                    </tr>
                    <c:forEach var="account" items="${accounts}">
                        <tr class="flex flex-row px-6 py-3 border-b border-[#84817a] items-center">
                            <td class="w-1/2 text-left">
                                <c:out value="${fn:substring(account.accountNumber, 0, 4)} ${fn:substring(account.accountNumber, 4, 8)} ${fn:substring(account.accountNumber, 8, 12)} ${fn:substring(account.accountNumber, 12, 16)}" />
                            </td>
                            <td class="w-1/6 text-right">
                                <c:out value="${account.balance}"/>
                            </td>
                            <td class="w-1/6 text-right">
                                <c:out value="${account.type}"/>
                            </td>
                            <td class="w-1/6">
                                <div class="flex flex-row gap-2 justify-end">
                                    <form action="/create_transaction" method="get" class="m-0">
                                        <input type="hidden" name="to" value="${account.accountNumber}" />
                                         <button class="p-1 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                                            <span class="h-4 w-4 flex justify-center items-center">
                                                <%@include file="../icons/arrow-down-tray.svg" %>
                                            </span>
                                        </button>
                                    </form>
                                    <form action="/create_transaction" method="get" class="m-0">
                                        <input type="hidden" name="from" value="${account.accountNumber}" />
                                        <button type="Submit" class="p-1 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                                            <span class="h-4 w-4 flex justify-center items-center">
                                                <%@include file="../icons/arrow-up-tray.svg" %>
                                            </span>
                                        </button>
                                    </form>
                                    <form action="/account" method="get" class="m-0">
                                        <input type="hidden" name="id" value="${account.id}" />
                                        <button type="submit" class="p-1 border border-[#706fd3] hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                                            <span class="h-4 w-4 flex justify-center items-center">
                                                <%@include file="../icons/arrows-right-left.svg" %>
                                            </span>
                                        </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>
