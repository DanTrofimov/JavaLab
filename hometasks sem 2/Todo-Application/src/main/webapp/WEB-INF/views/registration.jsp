<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href='<c:url value="/styles/auth.css"/>' type="text/css">
</head>
<body>
    <t:header>
    </t:header>
    <div class="registration-content">
        <div>
            <t:registration-form>
            </t:registration-form>
            <c:if test="${empty sessionScope.get('currentUser')}">
                <p class="link-container">
                    <a href="<c:url value="/sign-in"/>">Sign In</a>
                </p>
            </c:if>
            <c:if test="${not empty sessionScope.get('currentUser')}">
                <p class="link-container">
                    <a href="<c:url value="/main"/>">Profile</a>
                </p>
            </c:if>
            <c:if test="${not empty sessionScope.get('signUpError')}">
                <p class="link-container error-message">
                    ${sessionScope.get('signUpError')}
                </p>
            </c:if>
        </div>
        <div>
            <img src="${pageContext.request.contextPath}/assets/welcome-image.png" alt="welcome-image" class="welcome-image">
        </div>
    </div>
</body>
</html>
