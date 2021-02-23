<#--<#import "spring.ftl" as spring/>-->

<#macro signInForm>
<form action="sign-in" method="POST" class="form">
    <h3 class="form__title">Sign in</h3>
    <input name="email" placeholder="e-mail">
    <input name="password" type="password" placeholder="password">
    <input type="submit" value="sing in" class="send-button">
</form>

<link rel="stylesheet" href='<@spring.url '/styles/form.css' />' type="text/css">
</#macro>