$(document).ready(function() {

});


    var springApp = angular.module("springApp", []);

springApp.controller("appController",  function($scope, $http){
    $scope.friends = [];
    $scope.selection = [];
    $scope.profile = {};


    $scope.checkedFriends = [];
    $scope.toggleCheck = function (friend) {
        friend.selected = !friend.selected;
    };



    $scope.orderField = 'username';
    $scope.orderInc = false;

    $http.get("https://localhost:8443/api/profile").then(
        function (response){
            console.log(response);
            $scope.profile = response;
            $scope.getAcceptedFriends();
        },
        function (error){
        }
    );

    $scope.getAcceptedFriends = function() {
        var config = { params: { select: "accepted"}};
        $http.get("https://localhost:8443/api/friends", config).then(
            function (response) {
                console.log(response);
                var friendsList = response.data;
                for (var key in friendsList) {
                    friendsList[key].selected = false;
                }
                $scope.friends = friendsList;
            },
            function (error) {
            }
        );
    }
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
        for (var key in $scope.friends) {
            if ($scope.friends[key].selected) {
                $http.get("https://localhost:8443/api/location/" + $scope.friends[key].id, config).then(
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
            }
        }
    };

    $scope.setHour = function(){
        remove2GisMapTracks();
        var config = { params: { period: "hour"}};
        for (var key in $scope.friends) {
            if ($scope.friends[key].selected) {
                $http.get("https://localhost:8443/api/location/" + $scope.friends[key].id, config).then(
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
            }
        }
    };

    $scope.setDay = function(){
        remove2GisMapTracks();
        var config = { params: { period: "day"}};
        for (var key in $scope.friends) {
            if ($scope.friends[key].selected) {
                $http.get("https://localhost:8443/api/location/" + $scope.friends[key].id, config).then(
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
            }
        }
    };

    $scope.clear = function(){
        remove2GisMapTracks();
        remove2GisMapMarkers();
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



