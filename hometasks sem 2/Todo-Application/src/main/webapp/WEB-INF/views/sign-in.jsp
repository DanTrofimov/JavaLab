<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href='<c:url value="/styles/auth.css"/>' type="text/css">
</head>
<body>
    <t:header>
    </t:header>
    <div class="sign-in-content">
        <div>
            <t:sign-in-form>
            </t:sign-in-form>
            <p class="link-container">
                <a href='<c:url value="/registration"/>'>Back to registration</a>
            </p>
            <c:if test="${not empty sessionScope.get('sign-in-error')}">
                <p class="link-container error-message">
                        ${sessionScope.get('sign-in-error')}
                </p>
            </c:if>
        </div>
        <div>
            <img src="${pageContext.request.contextPath}/assets/welcome-image.png" alt="welcome-image" class="welcome-image">
        </div>
    </div>
</body>
</html>
