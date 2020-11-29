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
    <div>
        <button style="font-size: 35px; font-family: Consolas, sans-serif; height: 80px; width: 320px; background-color: black; border: 3px dashed white; color: white; cursor: pointer" class="profile-button" type="submit">to the profile</button>
        <c:if test="${not empty sessionScope.get('info')}">
            <p style="margin-top: 15px; font-size: 30px; text-align: center; color: #ff707d; font-family: Consolas, sans-serif">
                    ${sessionScope.get('info')}
            </p>
        </c:if>
    </div>
</form>
</body>
</html>
