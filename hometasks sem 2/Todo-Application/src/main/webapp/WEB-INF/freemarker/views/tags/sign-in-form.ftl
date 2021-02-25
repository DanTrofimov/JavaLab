<#--<#import "spring.ftl" as spring/>-->

<#macro signInForm>
<form action="sign-in" method="POST" class="form">
    <h3 class="form__title"><@spring.message 'sign_in_page.title'/></h3>
    <input name="email" placeholder="<@spring.message 'sign_in_page.placeholder.email'/>">
    <input name="password" type="password" placeholder="<@spring.message 'sign_in_page.placeholder.password'/>">
    <input type="submit" value="<@spring.message 'sign_in_page.placeholder.button'/>" class="send-button">
</form>

<link rel="stylesheet" href='<@spring.url '/styles/form.css' />' type="text/css">
</#macro>