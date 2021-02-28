<#macro registrationForm>
    <@spring.bind "signUpFormDto"/>
    <form action="<@spring.url'/registration'/>" method="POST" class="form">
        <h3 class="form__title"><@spring.message 'sign_up_page.registration.title'/></h3>
<#--        пока не нашел способа добавить локализованные плейсхолдеры-->
<#--        <input name="name" placeholder="<@spring.message 'sign_up_page.registration.placeholder.name'/>">-->
<#--        assigns-->

        <#assign namePlaceholder><@spring.message 'sign_up_page.registration.placeholder.name'/></#assign>
        <#assign emailPlaceholder><@spring.message 'sign_up_page.registration.placeholder.email'/></#assign>
        <#assign passPlaceholder><@spring.message 'sign_up_page.registration.placeholder.password'/></#assign>
        <#assign passrepPlaceholder><@spring.message 'sign_up_page.registration.placeholder.password_repeat'/></#assign>


        <@spring.formInput "signUpFormDto.name" "placeholder=" + namePlaceholder />
        <@spring.showErrors "<br>" "error-message"/>
        <@spring.formInput "signUpFormDto.email" "placeholder=" + emailPlaceholder/>
        <@spring.showErrors "<br>" "error-message"/>
        <@spring.formPasswordInput "signUpFormDto.password" "placeholder=" + passPlaceholder/>
        <@spring.showErrors "<br>" "error-message"/>
        <@spring.formPasswordInput "signUpFormDto.repeatPassword" "placeholder=" + passrepPlaceholder/>
        <@spring.showErrors "<br>" "error-message"/>
        <#if namesErrorMessage??>
            <p class="error-message">${namesErrorMessage}</p>
        </#if>
        <div class="checkbox-container">
            <@spring.formCheckbox "signUpFormDto.userAgreement" "placeholder='repeat password'"/>
            <p class="agreement"><@spring.message 'sign_up_page.registration.placeholder.agreemet'/></p>
            <@spring.showErrors "<br>" "error-message"/>
        </div>
        <input type="submit" value="<@spring.message 'sign_up_page.registration.button'/>" class="send-button">
    </form>

<link rel="stylesheet" href="<@spring.url '/styles/form.css' />" type="text/css">
</#macro>