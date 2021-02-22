<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Admin panel</title>
    <link rel="stylesheet" href='<c:url value="/styles/main.css"/>' type="text/css">
</head>
<body>
<script src="<c:url value="/scripts/todo.js"/>"></script>
<script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"/>"></script>
<script src="<c:url value="https://yastatic.net/jquery/3.3.1/jquery.min.js"/>"></script>
<script src="<c:url value="/scripts/pieTest.js"/>"></script>

<t:header>
</t:header>
<div class="main-content main-content--admin">
    <div class="user">
        <div class="user__name">
            <p>Admin:</p>
            <p>${sessionScope.get("currentUser").getName()}</p>
            <form action="sign-out" method="get"><input type="submit" class="sign-out-button" value="sign-out"></form>
        </div>
        <div class="user__groups user__groups--admin">
            <a href="<c:url value="/admin"/>"><t:group title="users"></t:group></a>
            <a href="<c:url value="/admin-add"/>"><t:group title="+ add todo" color="#f2eab2"></t:group></a>
        </div>
    </div>
    <div class="user-list">
        <c:forEach var="user" items="${sessionScope.get('allUsers')}">
            <t:user-card name="${user.getName()}" email="${user.getEmail()}" userId="${user.getId()}">
            </t:user-card>
        </c:forEach>
        <c:if test="${empty sessionScope.get('allUsers')}">
            <div class="empty-todos empty-users">
                <div>
                    <img src="${pageContext.request.contextPath}/assets/empty-box.png" alt="empty-users" class="empty-todos-image empty-users-image">
                    <p>there are no users here yet</p>
                </div>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>