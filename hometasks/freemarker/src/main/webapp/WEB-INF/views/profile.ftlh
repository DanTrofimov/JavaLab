<#ftl encoding="UTF-8"/>
<#import "./styles/login-css.ftl" as c>
<#import "./styles/profile-css.ftl" as p>

<html>
<head>
    <title>profile</title>
    <style>
        <@c.logCss></@c.logCss>
    </style>
    <style>
        <@p.profileCss></@p.profileCss>
    </style>
</head>
<body class="container">
    <div>
        <h1 class="profile-info">here is your profile</h1>
        <ol class="users-list">
            <p>recent users:</p>
            <#list recentUsers as users>
                <li>${users.name} ${users.age}</li>
        </#list>
        </ol>
    </div>
</body>
</html>
