<#macro pieChart userId>
    <div class="chart-container">
        <canvas id="${userId}-myPieChart"></canvas>
    </div>

    <script src="<@spring.url '/scripts/pieTest.js' />"></script>

    <script>
        getUsersData(${userId}, "${springMacroRequestContext.contextPath}")
    </script>
</#macro>