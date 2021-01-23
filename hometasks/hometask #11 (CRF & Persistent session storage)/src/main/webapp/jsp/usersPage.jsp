<%@ page import="ru.itdrive.web.models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${user.id}</h1>
<h2>${user.email}</h2>
<h3>${_csrf_token}</h3>
<form action="/users?action=delete&userId=${user.id}" method="post">
    <input type="hidden" value="${_csrf_token}" name="_csrf_token">
    <input type="submit" value="Delete">
</form>
</body>
</html>
