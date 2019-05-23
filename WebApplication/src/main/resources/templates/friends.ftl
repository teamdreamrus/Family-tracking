<#import "parts/page.ftl" as pageTemplate>

<@pageTemplate.page "Друзья">
    <div class="container-fluid"  ng-app="springApp" ng-controller="appController">
        <div class="row fill-viewport">
            <div class="col-lg-6 col-md-6 col-sm-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading rounded-top centered"><h3><a class="text-center white" href="#">Друзья</a></h3></div>
                            <div class="panel-body fixed-tables">

                            <table id="friendsTable" class="table table-striped fill-viewport">
                                <thead>
                                <tr>
                                    <th><i class="fas fa-users" hover-class="fa-user-alt"
                                           ng-click="orderField = 'id'; orderInc = !orderInc"></i> Никнейм</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr ng-repeat="item in friends | orderBy: orderField : orderInc ">
                                    <td>
                                        <div class="container">
                                                <div class="row justify-content-center">
                                                    <div class="col">
                                                        <h3 class="centered">{{ item.username }}</h3>
                                                    </div>
                                                </div>
                                                <div class="row justify-content-center">
                                                    <div class="col">
                                                        <button class="btn btn-default" ng-click="deleteFriendship(item.id)">Удалить</button>
                                                    </div>
                                                    <div class="col">
                                                        <button class="btn btn-default" ng-click="acceptFriendship(item.id)" ng-if="!item.accepted">Подтвердить</button>
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
            <div class="d-block d-md-none w-100"></div>
            <div class="col-lg-6 col-md-6 col-sm-12">
                <div class="panel panel-primary">
                    <div class="panel-heading rounded-top centered"><h3><a class="text-center white" href="#">Все пользователи</a></h3></div>
                    <div class="panel-body  fixed-tables">

                        <table id="usersTable" class="table table-striped fill-viewport">
                            <thead>
                            <tr>
                                <th><i class="fas fa-users" hover-class="fa-user-alt"
                                       ng-click="orderField = 'id'; orderInc = !orderInc"></i> Никнейм
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in users  | orderBy: orderField : orderInc ">
                                <td>
                                    <div class="container">
                                        <div class="row justify-content-center">
                                            <div class="col">
                                                <h3 class="centered">{{ item.username }}</h3>
                                            </div>
                                        </div>
                                        <div class="row justify-content-center">
                                            <div class="col">
                                                <button class="btn btn-default" ng-click="addNewFriendshipRequest(item.id)">Подать заявку в друзья</button>
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


    </div>
</div>


    <script src="/resources/js/angular.min.js"></script>
    <script src='/resources/js/friends.js'></script>
    <script>
        function funonload(){
            $('#friendsTable').fixedHeaderTable({ footer: false, cloneHeadToFoot: false, fixedColumn: false });
            $('#usersTable').fixedHeaderTable({ footer: false, cloneHeadToFoot: false, fixedColumn: false });
            $('#main').height($(window).height());
        }  window.onload = funonload;
    </script>
</@pageTemplate.page>
