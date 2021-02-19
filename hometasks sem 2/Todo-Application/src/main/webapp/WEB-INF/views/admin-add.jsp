<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Admin panel</title>
    <link rel="stylesheet" href='<c:url value="/styles/main.css"/>' type="text/css">
    <link rel="stylesheet" href='<c:url value="/styles/admin-form.css"/>' type="text/css">
</head>
<body>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"/>"></script>
<t:header>
</t:header>
<div class="main-content main-content--admin">
    <div class="user">
        <div class="user__name">
            <p>Admin:</p>
            <p>${sessionScope.get("current-user").getName()}</p>
            <form action="sign-out" method="get"><input type="submit" class="sign-out-button" value="sign-out"></form>
        </div>
        <div class="user__groups user__groups--admin">
            <a href="<c:url value="/admin"/>"><t:group title="users"></t:group></a>
            <a href="<c:url value="/admin-add"/>"><t:group title="+ add todo" color="#f2eab2"></t:group></a>
        </div>
    </div>
    <div class="add-todo-form__wrapper">
        <form action="admin-add" method="POST">
            <h3>Users:</h3>
            <div class="users">
                <c:forEach var="user" items="${sessionScope.get('all-users')}">
                    <p class="user-checkbox"><input name="users" value="${user.getId()}" type="checkbox"> ${user.getName()}</p>
                </c:forEach>
            </div>
            <h3>todo text:</h3>
            <textarea name="todo-text" class="todo-textarea" cols="30" rows="10" placeholder="type here"></textarea>
            <button type="submit" class="admin-todo-button">add</button>
        </form>
    </div>
</div>
</body>
</html>