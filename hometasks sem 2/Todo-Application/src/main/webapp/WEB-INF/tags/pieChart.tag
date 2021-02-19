<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="pie chart" pageEncoding="UTF-8"%>
<%@attribute name="userId" required="true" %>

<div class="chart-container">
    <canvas id="${userId}-myPieChart"></canvas>
</div>

<script src="<c:url value="/scripts/pieTest.js"/>"></script>

<script>
    getUsersData(${userId}, "${pageContext.request.contextPath}")
</script>