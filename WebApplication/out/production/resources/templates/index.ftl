<#import "parts/page.ftl" as pageTemplate>

<@pageTemplate.page "Family Tracking">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-default" style="margin-top:45px">
                <div class="panel-heading">
                    <h3 class="panel-title">Главная страница</h3>
                </div>
                <div class="panel-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>Меню</th>
                        </tr>
                        <tbody>
                        <tr>
                            <td><a class="alert-link" href="/profile">Личный кабинет</a></td>
                        </tr>
                        <tr>
                            <td><a class="alert-link" href="/friends">Друзья</a></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
</@pageTemplate.page>
