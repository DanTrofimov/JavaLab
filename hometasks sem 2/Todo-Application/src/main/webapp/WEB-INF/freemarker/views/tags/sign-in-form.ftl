<#macro signInForm>
<@spring.bind "signInFormDto"/>
    <form action="sign-in" method="POST" class="form">
    <h3 class="form__title"><@spring.message 'sign_in_page.title'/></h3>

    <#assign emailPlaceholder><@spring.message 'sign_in_page.placeholder.email'/></#assign>
    <#assign passPlaceholder><@spring.message 'sign_in_page.placeholder.password'/></#assign>


    <@spring.formInput "signInFormDto.email" "placeholder=" + emailPlaceholder/>
    <@spring.showErrors "<br>" "error-message"/>
    <@spring.formPasswordInput "signInFormDto.password" "placeholder=" + passPlaceholder/>
    <@spring.showErrors "<br>" "error-message"/>

    <input type="submit" value="<@spring.message 'sign_in_page.placeholder.button'/>" class="send-button">
</form>

<link rel="stylesheet" href='<@spring.url '/styles/form.css' />' type="text/css">
</#macro>