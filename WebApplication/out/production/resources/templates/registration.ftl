<#import "parts/login.ftl" as registrationForm>
<#import "parts/page.ftl" as pageTemplate>
<@pageTemplate.page "Регистрация">
    <div class="container">
    <div class="row justify-content-md-center">
        <div class="col col-md-auto">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading rounded-top">
                    <h3 class="panel-title text-center">Регистрация</h3>
                </div>
                <div class="panel-body">
                    <#if RequestParameters.error??>
                        <div class="alert alert-info" role="alert">Имя пользователя занято</div>
                    </#if>

                    <@registrationForm.login "/registration" "Подтвердить"/>

                </div>
            </div>
        </div>
    </div>
    </div>
</@pageTemplate.page>