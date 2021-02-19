<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@tag description="user's card" pageEncoding="UTF-8"%>
<%@attribute name="name" required="true" %>
<%@attribute name="email" required="true" %>
<%@attribute name="userId" required="true" %>
<%--<%@attribute name="statistics" required="true" %>--%>

<div class="user-card">
    <div>${name}</div>
    <div>${email}</div>
    <div>
        <t:pieChart  userId="${userId}">
        </t:pieChart>
    </div>
</div>

<link rel="stylesheet" href='<c:url value="/styles/user-card.css"/>' type="text/css">
