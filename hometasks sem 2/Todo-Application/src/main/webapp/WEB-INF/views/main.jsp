<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href='<c:url value="/styles/main.css"/>' type="text/css">
</head>
<body>
    <script src="<c:url value="/scripts/todo.js"/>"></script>
    <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"/>"></script>
    <script src="<c:url value="https://yastatic.net/jquery/3.3.1/jquery.min.js"/>"></script>
    <t:header>
    </t:header>
    <div class="main-content">
        <div class="user">
            <div class="user__name">
                <p>User:</p>
                <p>${sessionScope.get("currentUser").getName()}</p>
                <div class="button-container">
                    <form action="${pageContext.request.contextPath}/sign-out" method="get"><input type="submit" class="sign-out-button" value="sign-out"></form>
                    <button class="sign-out-button filter-button" id="filter-button">eye filter</button>
                </div>
            </div>
            <div class="user__groups">

                <a href="<c:url value="/main"/>"><t:group title="all todos" color="#f2eab2"></t:group></a>

                <c:forEach var="group" items="${sessionScope.get('groups')}">
                    <form action="filter-todos" method="get">
                        <button type="submit" value="${group.getId()}" name="group">
                            <t:group title="${group.getName()}"></t:group>
                        </button>
                    </form>
                </c:forEach>
            </div>
        </div>
        <div class="todo-list">
            <c:forEach var="todo" items="${sessionScope.get('todos')}">
                <t:todo text="${todo.getText()}" id="${todo.getId()}" todoGroup="${todo.getGroupId()}">
                </t:todo>
            </c:forEach>
            <form class="todo-form" action="add-todo" method="post">
                <input type="text" name="todoText" placeholder="type your task here...">
                <button type="submit">add</button>
            </form>
            <c:if test="${empty sessionScope.get('todos')}">
                <div class="empty-todos">
                    <div>
                        <img src="${pageContext.request.contextPath}/assets/empty-todos-image.png"  alt="emty-todos" class="empty-todos-image">
                        <p>waiting for your todos</p>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="statistics">
            <p>Your activity:</p>
            <t:pieChart userId="${sessionScope.get('currentUser').getId()}">
            </t:pieChart>
        </div>
    </div>
    <script src="<c:url value="/scripts/viewFilter.js"/>"></script>
</body>
</html>
