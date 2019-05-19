<#import "parts/page.ftl" as pageTemplate>

<@pageTemplate.page "Друзья">
<div ng-app="springApp">

    <div class="container-fluid" style="margin-top: 30px"  ng-controller="appController">
        <div class="row ">
                <div class="col-6">
                    <div class="panel panel-primary map">
                        <div class="panel-heading rounded-top centered"><h3><a class="text-center" href="/friends">Друзья</a></h3></div>
                            <div class="panel-body map">

                            <table id="friendsTable" class="table table-striped map">
                                <thead>
                                <tr>
                                    <th><i class="fas fa-angle-double-down" hover-class="fa-angle-down"
                                           ng-click="orderField = 'id'; orderInc = !orderInc"></i>Никнейм</th>
                                </tr>
                                </thead>
                                <tbody class="map">
                                <tr ng-repeat="item in friends | orderBy: orderField : orderInc">
                                    <td>
                                        <div class="container">
                                            <div class="row">
                                                <div class="row">
                                                    <div class="col-auto">
                                                        {{ item.username }}
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-auto">
                                                        <button class="btn btn-default">Удалить</button>
                                                    </div>
                                                    <div class="col-auto">
                                                        <button class="btn btn-default">Подтвердить</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                    </div>
                </div>
            </div>

            <div class="col-6">
                <div class="panel panel-primary map">
                    <div class="panel-heading rounded-top centered"><h3><a class="text-center" href="/friends">Никнейм</a></h3></div>
                    <div class="panel-body map">

                        <table id="usersTable" class="table table-striped map">
                            <thead>
                            <tr>
                                <th><i class="fas fa-angle-double-down" hover-class="fa-angle-down"
                                       ng-click="orderField = 'id'; orderInc = !orderInc"></i>Имя пользователя</th>
                            </tr>
                            </thead>
                            <tbody class="map">
                            <tr ng-repeat="item in users | orderBy: orderField : orderInc">
                                <td>
                                    <input type="checkbox" ng-checked="checkedFriends.indexOf(item) != -1" ng-click="toggleCheck(item)">
                                    {{ item.username }}
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>


    </div>
</div>
</div>

    <script src="/resources/js/angular.min.js"></script>
    <script src='/resources/js/friends.js'></script>
    <script>
        function funonload(){
            $('#friendsTable').fixedHeaderTable({ footer: false, cloneHeadToFoot: false, fixedColumn: false });
            $('#usersTable').fixedHeaderTable({ footer: false, cloneHeadToFoot: false, fixedColumn: false });
        }  window.onload = funonload;
    </script>
</@pageTemplate.page>
