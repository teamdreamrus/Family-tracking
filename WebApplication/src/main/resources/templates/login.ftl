<#import "parts/page.ftl" as pageTemplate>
<#import "parts/login.ftl" as loginForm>

<@pageTemplate.page "Авторизация">

        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 class="panel-title text-center">Авторизация</h3>
                </div>
                <div class="panel-body">
                    <@loginForm.login "/login" "Войти"/>

                </div>
            </div>
        </div>
</@pageTemplate.page>