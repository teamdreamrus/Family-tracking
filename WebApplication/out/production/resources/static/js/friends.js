$(document).ready(function() {

});


    var springApp = angular.module("springApp", []);

springApp.controller("appController",  function($scope, $http){
    $scope.friends = [];
    $scope.users = [];
    $scope.selection = [];
    $scope.search = {};

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
        },
        function (error){
        }
    );

    $http.get("https://localhost:8443/api/users").then(
        function (response){
            console.log(response);
            var usersList = response.data;
            $scope.users = usersList;
        },
        function (error){
        }
    );

    $scope.getAllFriends = function(){
        $http.get("https://localhost:8443/api/friends/").then(
            function (response){
                console.log(response);
                var friendsList = response.data;
                $scope.friends = friendsList;
            },
            function (error){
            }
        );
    }

    $scope.getAllUsers = function(){
        $http.get("https://localhost:8443/api/users/").then(
            function (response){
                console.log(response);
                var usersList = response.data;
                $scope.users = usersList;
            },
            function (error){
            }
        );
    }

    $scope.acceptFriendship = function(id){
        $http.put("https://localhost:8443/api/friends/" + id.toString()).then(
            function (response){
                console.log(response);
                $scope.getAllFriends();
            },
            function (error){
            }
        );
    }

    $scope.deleteFriendship = function(id){
        $http.delete("https://localhost:8443/api/friends/" + id.toString()).then(
            function (response){
                console.log(response);
                $scope.getAllFriends();
            },
            function (error){
            }
        );
    }

    $scope.response = {};

    $scope.addNewFriendshipRequest = function(id){
        $http.post("https://localhost:8443/api/friends/" + id.toString()).then(
            function(response){
                console.log(response);
                alert("Заявка на дружбу подана");
            },
            function(error){

            }
        )
    }


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




