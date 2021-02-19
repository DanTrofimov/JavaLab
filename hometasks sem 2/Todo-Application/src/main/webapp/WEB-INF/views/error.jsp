<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href='<c:url value="/styles/error.css"/>' type="text/css">
</head>
<body>
<script src="<c:url value="/scripts/todo.js"/>"></script>
<t:header>
</t:header>
<div class="error-content">
    <h2>Error. Something went wrong.</h2>
</div>
</body>
</html>
