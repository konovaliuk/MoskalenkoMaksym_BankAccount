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
            <form action="/create_account" method="POST" class="flex flex-col gap-6">
                <div class="flex flex-col gap-1">
                    <span class="text-sm">Account type:</span>
                    <select name="type" id="type" class="bg-[#f7f1e3] px-2 py-1 border border-[#84817a] rounded-sm">
                      <option value="debit">Debit</option>
                      <option value="credit">Credit</option>
                    </select>
                </div>
                <div class="flex flex-col gap-1">
                    <span class="text-sm">Amount:</span>
                    <input type="number" name="amount" class="px-2 py-1 bg-[#f7f1e3] border border-[#84817a] rounded-sm w-full"/>
                </div>
                <div class="flex justify-center w-full">
                    <input type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer" value="Submit"/>
                </div>
            </form>
        </div>
    </body>
</html>
