<#macro registrationForm>
    <@spring.bind "signUpFormDto"/>
    <form action="<@spring.url'/registration'/>" method="POST" class="form">
        <h3 class="form__title"><@spring.message 'sign_up_page.registration.title'/></h3>
        <input name="name" placeholder="<@spring.message 'sign_up_page.registration.placeholder.name'/>">
        <@spring.formInput "signUpFormDto.email" "placeholder='e-mail'"/>
        <@spring.showErrors "<br>" "error-message"/>
        <@spring.formPasswordInput "signUpFormDto.password" "placeholder='password'"/>
        <@spring.showErrors "<br>" "error-message"/>
        <@spring.formPasswordInput "signUpFormDto.repeatPassword" "placeholder='repeat password'"/>
        <@spring.showErrors "<br>" "error-message"/>
        <#if namesErrorMessage??>
            <p class="error-message">${namesErrorMessage}</p>
        </#if>
        <div class="checkbox-container">
            <input type="checkbox" name="user-agreement">
            <p class="agreement"><@spring.message 'sign_up_page.registration.placeholder.agreemet'/></p>
        </div>
        <input type="submit" value="<@spring.message 'sign_up_page.registration.button'/>" class="send-button">
    </form>

<link rel="stylesheet" href="<@spring.url '/styles/form.css' />" type="text/css">
</#macro>