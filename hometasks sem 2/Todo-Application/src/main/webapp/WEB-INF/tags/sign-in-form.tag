<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="sign-in form" pageEncoding="UTF-8"%>

<form action="sign-in" method="POST" class="form">
    <h3 class="form__title">Sign in</h3>
    <input name="email" placeholder="e-mail">
    <input name="password" type="password" placeholder="password">
    <input type="submit" value="sing in" class="send-button">
</form>

<link rel="stylesheet" href='<c:url value="/styles/form.css"/>' type="text/css">
