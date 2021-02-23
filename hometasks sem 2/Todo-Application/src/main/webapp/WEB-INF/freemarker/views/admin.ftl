<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/group.ftl" as group/>
<#import "tags/user-card.ftl" as card/>
<#import "tags/headerImports.ftl" as imports/>
<#import "tags/footerImports.ftl" as footerImports/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin panel</title>
    <link rel="stylesheet" href='<@spring.url '/styles/main.css' />' type="text/css">
    <link rel="icon" href='<@spring.url "/assets/favicon.ico"/>' type="image/x-icon" />
    <@imports.imports />
</head>
<body>
    <script src="<@spring.url "/scripts/todo.js"/>"></script>
    <script src="<@spring.url "/scripts/pieTest.js"/>"></script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
    <script src="https://yastatic.net/jquery/3.3.1/jquery.min.js"></script>

    <@header.header />
    <div class="main-content main-content--admin">
        <div class="user">
            <div class="user__name">
                <p>Admin:</p>
                <p> ${currentUser.getName()} </p>
                <form action="sign-out" method="get"><input type="submit" class="sign-out-button" value="sign-out"></form>
            </div>
            <div class="user__groups user__groups--admin">
                <a href="<@spring.url "/admin"/>"><@group.group title="users" color=""/></a>
                <a href="<@spring.url "/admin-add"/>"><@group.group title="+ add todo" color="#f2eab2"/></a>
            </div>
        </div>
        <div class="user-list">
            <#if allUsers?has_content>
                <#list allUsers as user>
                    <@card.userCard name="${user.getName()}" email="${user.getEmail()}" userId="${user.getId()}"/>
                </#list>
                <#else>
                    <div class="empty-todos empty-users">
                        <div>
                            <img src="${ springMacroRequestContext.contextPath }/assets/empty-box.png" alt="empty-users" class="empty-todos-image empty-users-image">
                            <p>there are no users here yet</p>
                        </div>
                    </div>
            </#if>
        </div>
    </div>
    <@footerImports.footerImports />
</body>
</html>