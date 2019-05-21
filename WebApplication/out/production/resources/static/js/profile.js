$(document).ready(function() {

});


    var springApp = angular.module("springApp", []);

springApp.controller("appController",  function($scope, $http, $window){
    $scope.profile = {};
    $scope.orderField = 'username';
    $scope.orderInc = false;
    $http.get("https://localhost:8443/api/profile").then(
        function (response){
            console.log(response);
            $scope.profile = response;
            $scope.setOne();
        },
        function (error){
        }
    );

    $scope.printPersons = function(){

        for (var key in $scope.friends) {
            if ($scope.friends[key].selected){
                console.log($scope.friends[key].username);
            }
        }
    };

    $scope.response = {};
    $scope.setOne = function(){
        remove2GisMapMarkers();
        var config = { params: { period: "one"}};
            $http.get("https://localhost:8443/api/location/" + $scope.profile.id, config).then(
                function (response) {
                    console.log(response);
                    var location = response.data;
                    if (location !== undefined && location.length > 0) {
                        add2GisMapMarkers(location[0].latitude, location[0].longitude, location[0].username);
                        fit2GisMapMarkers();
                    }
                },
                function (error) {

                }
            );
    };

    $scope.setHour = function(){
        remove2GisMapTracks();
        var config = { params: { period: "hour"}};
            $http.get("https://localhost:8443/api/location/" + $scope.profile.id, config).then(
                function (response) {
                    console.log(response);
                    var unparsedCoordinates = response.data;
                    var parsedCoordinates = [];
                    for (var i in unparsedCoordinates){
                        var coordArray = [];
                        coordArray.push(unparsedCoordinates[i].latitude);
                        coordArray.push(unparsedCoordinates[i].longitude);
                        parsedCoordinates.push(coordArray);
                    }
                    console.log(parsedCoordinates);
                    add2GisMapTracks(parsedCoordinates);
                    fit2GisMapTracks();
                },
                function (error) {

                }
            );
    };

    $scope.setDay = function(){
        remove2GisMapTracks();
        var config = { params: { period: "day"}};
                $http.get("https://localhost:8443/api/location/" + $scope.profile.id, config).then(
                    function (response) {
                        console.log(response);
                        var unparsedCoordinates = response.data;
                        var parsedCoordinates = [];
                        for (var i in unparsedCoordinates){
                            var coordArray = [];
                            coordArray.push(unparsedCoordinates[i].latitude);
                            coordArray.push(unparsedCoordinates[i].longitude);
                            parsedCoordinates.push(coordArray);
                        }
                        console.log(parsedCoordinates);
                        add2GisMapTracks(parsedCoordinates);
                        fit2GisMapTracks();
                    },
                    function (error) {

                    }
                );
    };



    $scope.deleteAllData = function() {
        $http.delete("https://localhost:8443/api/location").then(
            function (response) {

            },
            function (error) {

            }
        );

    }

    $scope.deleteAccount = function(){
        $http.delete("https://localhost:8443/api/profile").then(
            function (response){
                $window.location.href = '/logout';
            },
            function (error){

            }
        );

    };


});


springApp.directive("hoverClass",function(){
    return function(scope, element, attribute){
        element.bind("mouseenter", function(){
            element.addClass(attribute.hoverClass);
        });
        element.bind("mouseleave", function(){
            element.removeClass(attribute.hoverClass);
        });
    }
});



