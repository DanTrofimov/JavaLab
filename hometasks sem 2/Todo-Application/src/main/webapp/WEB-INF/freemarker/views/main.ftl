<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/group.ftl" as userGroup/>
<#import "tags/todo.ftl" as userTodo/>
<#import "tags/pieChart.ftl" as pieChart/>
<#import "tags/headerImports.ftl" as imports/>

<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href='<@spring.url "/styles/main.css"/>' type="text/css">
    <link rel="stylesheet" href="<@spring.url '/styles/header.css' />" type="text/css">
    <@imports.imports />

</head>
<body>
    <script src="<@spring.url "/scripts/todo.js"/>"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
    <script src="https://yastatic.net/jquery/3.3.1/jquery.min.js"></script>

    <@header.header />
    <div class="main-content">
        <div class="user">
            <div class="user__name">
                <p>User:</p>
                <p>${currentUser.getName()}</p>
                <div class="button-container">
                    <form action="${ springMacroRequestContext.contextPath }/sign-out" method="get"><input type="submit" class="sign-out-button" value="sign-out"></form>
                    <button class="sign-out-button filter-button" id="filter-button">eye filter</button>
                </div>
            </div>
            <div class="user__groups">

                <a href="<@spring.url "/main"/>"><@userGroup.group title="all todos" color="#f2eab2"/></a>


                <#list groups as group>
                    <form action="filter-todos" method="get">
                        <button type="submit" value="${group.getId()}" name="group">
                            <@userGroup.group  title="${group.getName()}" color=""/>
                        </button>
                    </form>
                </#list>
            </div>
        </div>
        <div class="todo-list">
            <#if todos??>
                <#list todos as todo>
                    <@userTodo.todo text="${todo.getText()}" id="${todo.getId()}" todoGroup="${todo.getGroupId()}"/>
                </#list>
                <form class="todo-form" action="add-todo" method="post">
                    <input type="text" name="todoText" placeholder="type your task here...">
                    <button type="submit">add</button>
                </form>
                <#else>
                <div class="empty-todos">
                    <div>
                        <img src="${ springMacroRequestContext.contextPath }/assets/empty-todos-image.png"  alt="emty-todos" class="empty-todos-image">
                        <p>waiting for your todos</p>
                    </div>
                </div>
            </#if>
        </div>
        <div class="statistics">
            <p>Your activity:</p>
            <@pieChart.pieChart userId="${ currentUser.getId() }"/>
        </div>
    </div>