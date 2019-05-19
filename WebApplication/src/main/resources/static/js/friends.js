$(document).ready(function() {

});


    var springApp = angular.module("springApp", []);

springApp.controller("appController",  function($scope, $http){
    $scope.friends = [];
    $scope.users = [];
    $scope.selection = [];


    $scope.checkedFriends = [];
    $scope.toggleCheck = function (friend) {
        friend.selected = !friend.selected;
    };



    $scope.orderField = 'username';
    $scope.orderInc = false;
    $http.get("https://localhost:8443/api/friends").then(
        function (response){
            console.log(response);
            var friendsList = response.data;
            $scope.friends = friendsList;
            $scope.printPersons();
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

    $scope.acceptFriendship = function(id){
        $http.put("https://localhost:8443/api/friends/" + id.toString()).then(
            function (response){
                console.log(response);
            },
            function (error){
            }
        );
    }

    $scope.deleteFriendship = function(id){
        $http.delete("https://localhost:8443/api/friends/" + id.toString()).then(
            function (response){
                console.log(response);
            },
            function (error){
            }
        );
    }

    $scope.response = {};

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




