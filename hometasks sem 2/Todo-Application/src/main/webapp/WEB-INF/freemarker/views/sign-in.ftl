<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/sign-in-form.ftl" as signForm/>
<#import "tags/headerImports.ftl" as imports/>

<html>
<head>
    <title>Sign In</title>
    <link rel="stylesheet" href='<@spring.url "/styles/auth.css"/>' type="text/css">
    <@imports.imports />
</head>
<body>
    <@header.header />

    <div class="sign-in-content">
        <div>
            <@signForm.signInForm/>

            <p class="link-container">
                <a href='<@spring.url "/registration"/>'>Back to registration</a>
            </p>
            <#if signInError??>
                <p class="link-container error-message">
                    ${signInError}
                </p>
            </#if>
        </div>
        <div>
            <img src="${ springMacroRequestContext.contextPath }/assets/welcome-image.png" alt="welcome-image" class="welcome-image">
        </div>
    </div>
</body>
</html>
