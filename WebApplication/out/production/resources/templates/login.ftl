<#import "parts/page.ftl" as pageTemplate>
<#import "parts/login.ftl" as loginForm>

<@pageTemplate.page "Авторизация">
    <div class="container">
    <div class="row justify-content-md-center">
        <div class="col col-md-auto">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading rounded-top">
                    <h3 class="panel-title text-center white">Авторизация</h3>
                </div>
                <div class="panel-body">
                    <#if RequestParameters.logout??>
                        <div class="alert alert-info" role="alert">Вы вышли из аккаунта</div>
                    </#if>
                    <#if RequestParameters.error??>
                        <div class="alert alert-info" role="alert">Неправильные учётные данные</div>
                    </#if>

                    <@loginForm.login "/login" "Войти"/>

                </div>
            </div>
        </div>
    </div>
    </div>
</@pageTemplate.page>