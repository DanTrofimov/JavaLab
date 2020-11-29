<#ftl encoding="UTF-8"/>
<#import "./styles/login-css.ftl" as c>

<html>
<head>
    <title>login</title>
    <style>
        <@c.logCss></@c.logCss>
    </style>
</head>
<body class="container">
<form action="${ request.contextPath }/login" method="post">
    <div>
        <button class="profile-button" type="submit">to the profile</button>
        <#if info ??>
            <p class="user-info">
                ${ info }
            </p>
        <#else>
            <p class="user-info">
                not in the system
            </p>
        </#if>
    </div>
</form>
</body>
</html>
