<#--<#import "spring.ftl" as spring/>-->

<#macro registrationForm>
<form action="<@spring.url'/registration'/>" method="POST" class="form">
    <h3 class="form__title">Registration</h3>
    <input name="name" placeholder="name">
    <input name="email" placeholder="e-mail">
    <input name="password" type="password" placeholder="password">
    <input name="password-repeat" type="password" placeholder="repeat password">
    <div class="checkbox-container">
        <input type="checkbox" name="user-agreement">
        <p class="agreement">Agreement to processing user's data</p>
    </div>
    <input type="submit" value="sing up" class="send-button">
</form>

<link rel="stylesheet" href="<@spring.url '/styles/form.css' />" type="text/css">
</#macro>