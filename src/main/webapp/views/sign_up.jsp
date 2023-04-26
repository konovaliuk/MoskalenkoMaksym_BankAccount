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
            <div class="w-full flex flex-row border border-[#84817a] rounded-sm mb-8">
                <a class="w-1/2 flex items-center justify-center border-r border-[#84817a] py-1 cursor-pointer" href="/sign_in">
                    <span class="text-lg">Sign In<span>
                </a>
                <div class="w-1/2 flex items-center justify-center bg-[#706fd3] text-[#f7f1e3] py-1">
                    <span class="text-lg">Sign Up<span>
                </div>
            </div>
            <form action="/sign_up" method="POST" class="flex flex-col gap-6">
                <div class="flex flex-col gap-1">
                    <span class="text-sm">Login</span>
                    <input name="login" class="px-2 py-1 bg-[#f7f1e3] border border-[#84817a] rounded-sm"/>
                </div>
                <div class="flex flex-col gap-1">
                    <span class="text-sm">Password</span>
                    <input name="password" class="px-2 py-1 bg-[#f7f1e3] border border-[#84817a] rounded-sm" type="password"/>
                </div>
                <div class="flex justify-center w-full">
                    <input type="submit" class="px-4 py-2 bg-[#706fd3] hover:bg-[#474787] rounded-sm text-[#f7f1e3] cursor-pointer" value="Submit"/>
                </div>
            </form>
        </div>
    </body>
</html>
