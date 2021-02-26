<#import "spring.ftl" as spring/>
<#import "tags/header.ftl" as header/>
<#import "tags/registration-form.ftl" as regForm/>
<#import "tags/headerImports.ftl" as imports/>
<#import "tags/footerImports.ftl" as footerImports/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
    <link rel="stylesheet" href='<@spring.url "/styles/auth.css"/>' type="text/css">
    <link rel="icon" href='<@spring.url "/assets/favicon.ico"/>' type="image/x-icon" />
    <@imports.imports />
</head>
<body>
    <@header.header />
    <div class="registration-content">
        <div>
<#--            <@regForm.registrationForm />-->

            <@spring.bind "signUpForm"/>
            <form action="<@spring.url'/registration'/>" method="POST" class="form">
                <h3 class="form__title"><@spring.message 'sign_up_page.registration.title'/></h3>
                <input name="name" placeholder="<@spring.message 'sign_up_page.registration.placeholder.name'/>">
<#--                <input name="email" placeholder="<@spring.message 'sign_up_page.registration.placeholder.email'/>">-->
                <@spring.formInput "signUpForm.email"/>
                <@spring.showErrors "error-message"/>
                <input name="password" type="password" placeholder="<@spring.message 'sign_up_page.registration.placeholder.password'/>">
                <input name="password-repeat" type="password" placeholder="<@spring.message 'sign_up_page.registration.placeholder.password_repeat'/>">
                <div class="checkbox-container">
                    <input type="checkbox" name="user-agreement">
                    <p class="agreement"><@spring.message 'sign_up_page.registration.placeholder.agreemet'/></p>
                </div>
                <input type="submit" value="<@spring.message 'sign_up_page.registration.button'/>" class="send-button">
            </form>

            <link rel="stylesheet" href="<@spring.url '/styles/form.css' />" type="text/css">

            <#if currentUser??>
                <p class="link-container">
                    <a href="<@spring.url "/main"/>"><@spring.message 'sign_up_page.registration.profile'/></a>
                </p>
                <#else>
                <p class="link-container">
                    <a href="<@spring.url "/sign-in"/>"><@spring.message 'sign_up_page.registration.sign_in'/></a>
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
