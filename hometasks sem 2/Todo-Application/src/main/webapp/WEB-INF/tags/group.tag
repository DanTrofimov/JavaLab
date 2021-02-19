<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="user's group of todos" pageEncoding="UTF-8"%>
<%@attribute name="title" required="true" %>
<%@attribute name="color" required="false" %>

<div class="user-group" style="background-color: ${color}">
    <p>${title}</p>
</div>

<link rel="stylesheet" href='<c:url value="/styles/user-group.css"/>' type="text/css">
