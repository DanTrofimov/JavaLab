<#macro group title color>
    <div class="user-group" style="background-color: ${color}">
        <p>${title}</p>
    </div>

    <link rel="stylesheet" href="<@spring.url '/styles/user-group.css' />" type="text/css">
</#macro>