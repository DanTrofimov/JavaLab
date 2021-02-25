<#--<#import "spring.ftl" as spring/>-->

<#macro registrationForm>
<form action="<@spring.url'/registration'/>" method="POST" class="form">
    <h3 class="form__title"><@spring.message 'sign_up_page.registration.title'/></h3>
    <input name="name" placeholder="<@spring.message 'sign_up_page.registration.placeholder.name'/>">
    <input name="email" placeholder="<@spring.message 'sign_up_page.registration.placeholder.email'/>">
    <input name="password" type="password" placeholder="<@spring.message 'sign_up_page.registration.placeholder.password'/>">
    <input name="password-repeat" type="password" placeholder="<@spring.message 'sign_up_page.registration.placeholder.password_repeat'/>">
    <div class="checkbox-container">
        <input type="checkbox" name="user-agreement">
        <p class="agreement"><@spring.message 'sign_up_page.registration.placeholder.agreemet'/></p>
    </div>
    <input type="submit" value="<@spring.message 'sign_up_page.registration.button'/>" class="send-button">
</form>

<link rel="stylesheet" href="<@spring.url '/styles/form.css' />" type="text/css">
</#macro>