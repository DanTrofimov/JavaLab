<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/registration-form.ftl" as regForm/>
<#import "tags/headerImports.ftl" as imports/>
<#import "tags/footerImports.ftl" as footerImports/>

<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href='<@spring.url "/styles/auth.css"/>' type="text/css">
    <@imports.imports />
</head>
<body>
    <@header.header />
    <div class="registration-content">
        <div>
            <@regForm.registrationForm />
            <#if currentUser??>
                <p class="link-container">
                    <a href="<@spring.url "/main"/>">Profile</a>
                </p>
                <#else>
                <p class="link-container">
                    <a href="<@spring.url "/sign-in"/>">Sign In</a>
                </p>
            </#if>
            <#if signUpError??>
                <p class="link-container error-message">
                    ${ signUpError }
                </p>
            </#if>
        </div>
        <div>
            <img src="${ springMacroRequestContext.contextPath }/assets/welcome-image.png" alt="welcome-image" class="welcome-image">
        </div>
    </div>
    <@footerImports.footerImports />
</body>
</html>
