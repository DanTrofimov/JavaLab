<#macro signInForm>
<@spring.bind "signInFormDto"/>
    <form action="sign-in" method="POST" class="form">
    <h3 class="form__title"><@spring.message 'sign_in_page.title'/></h3>

    <@spring.formInput "signInFormDto.email" "placeholder='e-mail'"/>
    <@spring.showErrors "<br>" "error-message"/>
    <@spring.formPasswordInput "signInFormDto.password" "placeholder='password'"/>
    <@spring.showErrors "<br>" "error-message"/>

    <input type="submit" value="<@spring.message 'sign_in_page.placeholder.button'/>" class="send-button">
</form>

<link rel="stylesheet" href='<@spring.url '/styles/form.css' />' type="text/css">
</#macro>