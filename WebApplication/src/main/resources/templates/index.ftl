<#import "parts/page.ftl" as pageTemplate>

<@pageTemplate.page "Family Tracking">
<div ng-app="springApp">

    <div class="container-fluid" style="margin-top: 30px"  ng-controller="appController">
        <div class="row ">
            <div class="col-2">
                <div class="panel panel-primary">
                    <div class="panel-heading rounded-top centered"><h3><a class="text-center" href="/friends">Друзья</a></h3></div>
                        <div class="panel-body">

                        <table id="friendsTable" class="table table-striped map">
                            <thead>
                            <tr>
                                <th><i class="fas fa-angle-double-down" hover-class="fa-angle-down"
                                       ng-click="orderField = 'id'; orderInc = !orderInc"></i> ФИО</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr ng-repeat="item in friends | orderBy: orderField : orderInc">
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
        <div class="col-10">
            <div class="panel panel-primary">
                <div class="panel-heading text-center rounded-top"><h3>Карта</h3></div>
                <div class="panel-body panel-primary">
                    <div class="row justify-content-around">
                            <div class="col">
                                <button class="btn btn-default" ng-click="setOneDay()">
                                    Последнее место
                                </button>
                            </div>
                            <div class="col">
                                <button class="btn btn-default" ng-click="setHour()">
                                    Последний час
                                </button>
                            </div>
                            <div class="col">
                                <button class="btn btn-default" ng-click="editedPerson(newPerson)">
                                    Последние сутки
                                </button>
                            </div>
                    </div>
                    <div class="row justify-content-center">
                        <div class="col">
                            <div id='map' class="map"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



</div>

    <script src='https://maps.api.2gis.ru/2.0/loader.js?pkg=full'></script>
    <script src='/resources/js/doubleGisScript.js'></script>
    <script src="/resources/js/angular.min.js"></script>
    <script src='/resources/js/main.js'></script>
    <script> function funonload(){ create2GisMap(54, 82); console.log("Access"); BootstrapScrollTable('friendsTabl'); }  window.onload = funonload; </script>
</@pageTemplate.page>
