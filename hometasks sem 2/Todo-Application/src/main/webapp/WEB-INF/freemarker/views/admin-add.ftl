<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/group.ftl" as group/>
<#import "tags/headerImports.ftl" as imports/>
<#import "tags/footerImports.ftl" as footerImports/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin panel</title>
    <link rel="stylesheet" href='<@spring.url "/styles/main.css"/>' type="text/css">
    <link rel="stylesheet" href='<@spring.url "/styles/admin-form.css"/>' type="text/css">
    <link rel="icon" href='<@spring.url "/assets/favicon.ico"/>' type="image/x-icon" />
    <@imports.imports />
</head>
<body>
<script src="<@spring.url "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"/>"></script>
<@header.header />

    <div class="main-content main-content--admin">
        <div class="user">
            <div class="user__name">
                <p><@spring.message "admin.page.admin"/>:</p>
                <p>${currentUser.getName()}</p>
                <form action="sign-out" method="get"><input type="submit" class="sign-out-button" value="<@spring.message "admin.sign_out"/>"></form>
            </div>
            <div class="user__groups user__groups--admin">
                <a href="<@spring.url "/admin"/>"><@group.group title="users" color=""/></a>
                <a href="<@spring.url "/admin-add"/>"><@group.group title="+ add todo" color="#f2eab2"/></a>
            </div>
        </div>
        <div class="add-todo-form__wrapper">
            <form action="admin-add" method="POST">
                <h3><@spring.message "admin.add.form.title"/>:</h3>
                <div class="users">
                    <#list allUsers as user>
                        <p class="user-checkbox"><input name="users" value="${user.getId()}" type="checkbox"> ${user.getName()}</p>
                    </#list>
                </div>
                <h3><@spring.message "admin.add.form.textarea.title"/>:</h3>
                <textarea name="todoText" class="todo-textarea" cols="30" rows="10" placeholder="<@spring.message "admin.add.form.textarea.placehorder"/>"></textarea>
                <button type="submit" class="admin-todo-button"><@spring.message "admin.add.form.button"/></button>
            </form>
        </div>
    </div>
    <@footerImports.footerImports />
</body>
</html>