<#import "spring.ftl" as spring/>
<#import "../tags/header.ftl" as header/>
<#import "../tags/group.ftl" as group/>

<html>
<head>
    <title>Admin panel</title>
    <link rel="stylesheet" href='<@spring.url "/styles/main.css"/>' type="text/css">
    <link rel="stylesheet" href='<@spring.url "/styles/admin-form.css"/>' type="text/css">
</head>
<body>
<script src="<@spring.url "https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"/>"></script>
<@header.header />

<div class="main-content main-content--admin">
    <div class="user">
        <div class="user__name">
            <p>Admin:</p>
            <p>${currentUser.getName()}</p>
            <form action="sign-out" method="get"><input type="submit" class="sign-out-button" value="sign-out"></form>
        </div>
        <div class="user__groups user__groups--admin">
            <a href="<@spring.url "/admin"/>"><@group.group title="users" color=""/></a>
            <a href="<@spring.url "/admin-add"/>"><@group.group title="+ add todo" color="#f2eab2"/></a>
        </div>
    </div>
    <div class="add-todo-form__wrapper">
        <form action="admin-add" method="POST">
            <h3>Users:</h3>
            <div class="users">
                <#list allUsers as user>
                    <p class="user-checkbox"><input name="users" value="${user.getId()}" type="checkbox"> ${user.getName()}</p>
                </#list>
            </div>
            <h3>todo text:</h3>
            <textarea name="todoText" class="todo-textarea" cols="30" rows="10" placeholder="type here"></textarea>
            <button type="submit" class="admin-todo-button">add</button>
        </form>
    </div>
</div>