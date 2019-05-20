<#import "parts/page.ftl" as pageTemplate>

<@pageTemplate.page "Профиль">

    <div class="container-fluid" ng-app="springApp" ng-controller="appController">
        <div class="row fill-viewport">
            <div class="col-3">
                <div class="panel panel-primary">
                    <div class="panel-heading rounded-top centered"><h3 class="text-center">Управление профилем</h3></div>
                    <div class="panel-body fixed-tables">
                        <div class="container">
                            <div class="row justify-content-center">
                                <div class="col">
                                    <h3 class="btn btn-default fill-width wrap-top" ng-bind="profile.username"></h3>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col">
                                    <button class="btn btn-default fill-width wrap-top" ng-click="deleteAllData()">Удалить все данные</button>
                                </div>
                            </div>
                            <div class="row justify-content-center">
                                <div class="col">
                                    <button class="btn btn-default fill-width wrap-top" ng-click="deleteAccount()">Удалить аккаунт</button>
                                </div>
                            </div>

                        </div>

                    </div>
                </div>
            </div>
            <div class="d-block d-md-none w-100"></div>
            <div class="col-9">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center rounded-top"><h3>Моё местоположение</h3></div>
                    <div class="panel-body panel-primary fixed-tables">
                        <div class="row justify-content-around">
                            <div class="col">
                                <button class="btn btn-default" ng-click="setOne()">
                                    Последнее место
                                </button>
                            </div>
                            <div class="col">
                                <button class="btn btn-default" ng-click="setHour()">
                                    Последний час
                                </button>
                            </div>
                            <div class="col">
                                <button class="btn btn-default" ng-click="setDay()">
                                    Последние сутки
                                </button>
                            </div>
                        </div>
                        <div class="row justify-content-center fill-viewport">
                            <div class="col">
                                <div id='map' class="fill-viewport"></div>
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
    <script src='/resources/js/profile.js'></script>
    <script>
        function funonload(){
            create2GisMap(54, 82); console.log("Access");

        }  window.onload = funonload;
    </script>
</@pageTemplate.page>
