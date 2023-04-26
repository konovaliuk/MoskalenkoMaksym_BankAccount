<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
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
                    Processing credit accounts:
                </span>
                <div class="flex flex-row gap-3 items-center">
                    <form  action="/create_account" method="get" class="m-0">
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
                        <th class="w-1/3 text-left">Account ID</th>
                        <th class="w-1/3 text-left">Profile ID</th>
                        <th class="w-1/9 text-right">Balance</th>
                        <th class="w-2/9 text-right">Actions</th>
                    </tr>
                    <c:forEach var="account" items="${accounts}">
                        <tr class="flex flex-row px-6 py-3 border-b border-[#84817a] items-center">
                            <td class="w-1/3 text-left">
                                <c:out value="${account.id}"/>
                            </td>
                            <td class="w-1/3 text-left">
                                <c:out value="${account.profileId}"/>
                            </td>
                            <td class="w-1/6 text-right">
                                <c:out value="${account.balance}"/>
                            </td>
                            <td class="w-1/6">
                                <div class="flex flex-row gap-2 justify-end">
                                    <form action="/credit_approve" method="POST" class="m-0">
                                        <input type="hidden" name="status" value="open" />
                                        <input type="hidden" name="account_id" value="${account.id}" />
                                        <button type="submit" class="p-1 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                                            <span class="h-4 w-4 flex justify-center items-center">
                                                <%@include file="../icons/check.svg" %>
                                            </span>
                                        </button>
                                    </form>
                                    <form action="/credit_approve" method="POST" class="m-0">
                                        <input type="hidden" name="status" value="closed" />
                                        <input type="hidden" name="account_id" value="${account.id}" />
                                        <button type="submit" class="p-1 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                                            <span class="h-4 w-4 flex justify-center items-center">
                                                <%@include file="../icons/no-symbol.svg" %>
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
