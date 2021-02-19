<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="registration form" pageEncoding="UTF-8"%>

<form action="registration" method="POST" class="form">
    <h3 class="form__title">Registration</h3>
    <input name="name" placeholder="name">
    <input name="email" placeholder="e-mail">
    <input name="password" type="password" placeholder="password">
    <input name="password-repeat" type="password" placeholder="repeat password">
    <div class="checkbox-container">
        <input type="checkbox" name="user-agreement">
        <p class="agreement">Agreement to processing user's data</p>
    </div>
    <input type="submit" value="sing up" class="send-button">
</form>

<link rel="stylesheet" href='<c:url value="/styles/form.css"/>' type="text/css">
