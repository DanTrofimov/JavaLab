<#import "pieChart.ftl" as macro/>

<#macro userCard name email userId>
 <div class="user-card">
    <div>${name}</div>
    <div>${email}</div>
    <div>
        <@macro.pieChart userId=userId/>
    </div>
</div>

<link rel="stylesheet" href='<@spring.url '/styles/user-card.css' />' type="text/css">
</#macro>