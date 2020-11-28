<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body
    style="background: black; height: 100vh; display: grid; place-items: center;"
>
<form action="${pageContext.request.contextPath}/login" method="post">
    <button style="font-size: 35px; font-family: Consolas, sans-serif; height: 80px; width: 320px; background-color: black; border: 3px dashed white; color: white; cursor: pointer" class="profile-button" type="submit">to the profile</button>
</form>
</body>
</html>
