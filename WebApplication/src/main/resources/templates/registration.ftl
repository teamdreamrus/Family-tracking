<#import "parts/login.ftl" as registrationForm>
<#import "parts/page.ftl" as pageTemplate>
<@pageTemplate.page "Регистрация">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 class="panel-title">Registration with Username and Password</h3>
                </div>
                <div class="panel-body">
                    <@registrationForm.login "/registration" "Подтвердить"/>

                </div>
            </div>
        </div>
</@pageTemplate.page>