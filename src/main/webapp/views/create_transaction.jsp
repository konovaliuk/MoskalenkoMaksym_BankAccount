<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Bank Account</title>
        <script src="https://cdn.tailwindcss.com"></script>
    </head>
    <body class="h-screen flex items-center justify-center bg-[#706fd3]">
        <div class="w-96 bg-[#f7f1e3] px-3 py-6 rounded-md">
            <form action="/create_transaction" method="POST" class="flex flex-col gap-6">
                <div class="flex flex-col gap-1">
                    <span class="text-sm">From:</span>
                    <div class="w-full flex flex-row gap-1">
                        <input id="source-account-input" name="from" class="px-2 py-1 bg-[#f7f1e3] border border-[#84817a] rounded-sm w-full" value="${from}"/>
                        <button onclick="setDefaultAccount('source-account-input')" type="button" class="p-2 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                            <span class="flex justify-center items-center w-4 h-4">
                                <%@include file="../icons/home.svg" %>
                            </span>
                        </button>
                    </div>
                    <div class="flex flex-row gap-2 items-center">
                        <span class="text-sm">External</span>
                        <input name="isSourceExternal" type="checkbox"/>
                    </div>
                </div>
                <div class="flex flex-col gap-1">
                    <span class="text-sm">To:</span>
                    <div class="w-full flex flex-row gap-1">
                        <input id="destination-account-input" name="to" class="px-2 py-1 bg-[#f7f1e3] border border-[#84817a] rounded-sm w-full" value="${to}"/>
                        <button onclick="setDefaultAccount('destination-account-input')" type="button" class="p-2 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                            <span class="flex justify-center items-center w-4 h-4">
                                <%@include file="../icons/home.svg" %>
                            </span>
                        </button>
                    </div>
                    <div class="flex flex-row gap-2 items-center">
                        <span class="text-sm">External</span>
                        <input name="isDestinationExternal" type="checkbox"/>
                    </div>
                </div>
                <div class="flex flex-col gap-1">
                    <span class="text-sm">Amount:</span>
                    <div class="w-full flex flex-row gap-1">
                        <input min="0" type="number" name="amount" class="px-2 py-1 bg-[#f7f1e3] border border-[#84817a] rounded-sm w-full"/>
                        <button type="button" class="p-1 border-[#706fd3] border hover:border-[#474787] rounded-sm text-[#706fd3] hover:text-[#474787] cursor-pointer">
                            <span class="px-2 flex justify-center items-center">
                                All
                            </span>
                        </button>
                    </div>
                </div>
                <div class="flex justify-center w-full">
                    <input type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer" value="Submit"/>
                </div>
            </form>
        </div>

        <script>
            function setDefaultAccount(inputId) {
                document.getElementById(inputId).value= '<%= request.getAttribute("defaultAccount") %>';
                console.log(document.getElementById(inputId))
            }
        </script>
    </body>
</html>
