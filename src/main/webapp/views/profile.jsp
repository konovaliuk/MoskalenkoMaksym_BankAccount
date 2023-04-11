<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Profile</title>
    </head>
    <body>
        <div style="display:flex; flex-direction:column; gap:4px;">
            <span>Profile ID: ${profile.id}</span>
            <span>Login: ${profile.login}</span>
            <span>Role: ${profile.role}</span>
        </div>
    </body>
</html>
