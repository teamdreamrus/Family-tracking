
var springApp = angular.module("springApp", []);

springApp.controller("appController",  function($scope, $http){
    $scope.friends = [];
    $scope.selection = [];


    $scope.checkedFriends = [];
    $scope.toggleCheck = function (friend) {
        friend.selected = !friend.selected;
    };



    $scope.orderField = 'username';
    $scope.orderInc = false;
    $http.get("https://localhost:8443/api/friends/"+"userId").then(
        function (response){
            console.log(response);
            var friendsList = response.data;
            for (var key in friendsList){
                friendsList[key].selected = false;
            }
            $scope.friends = friendsList;
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
    $scope.setOneDay = function(){

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

        $scope.newPerson = null;
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
        $scope.newPerson = null;
    };


    $scope.deletePerson = function(id){
        console.log("Number of deleted: "+id);
        $http.delete("http://localhost:8080/api/legalpersons/"+id.toString()).then(
            function (response){
                removeByAttr($scope.persons, "id", id);
            },
            function (error){

            }
        );

    };


    $scope.editPerson = function(person){
        console.log("Number of edited: "+person.id);
        $scope.newPerson = {};
        for (var key in person) {
            $scope.newPerson[key] = person[key];
        }
        $scope.boolValue = false;
    };

    $scope.editedPerson = function(editedPerson){
        console.log("Number of edited: "+editedPerson.id);
        $http.put("http://localhost:8080/api/legalpersons/"+editedPerson.id.toString(), editedPerson).then(
            function (response){
                console.log(response);
                if (response.data) {
                    $scope.boolValue = true;
                    var person = {};
                    for (var key in editedPerson) {
                        person[key] = editedPerson[key];
                    }
                    removeByAttr($scope.persons, "id", editedPerson.id);
                    $scope.newPerson = null;
                    $scope.persons.push(person);
                }
            },
            function (error){
            }
        );
    };

    $scope.cancelEditPerson = function(editedPerson){
        $scope.boolValue = true;
        $scope.newPerson = null;
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

function BootstrapScrollTable(tbl) {
    $('tbody').css('overflow-y', 'scroll');
    $('tbody').css('position', 'absolute');

    var hTable = parseInt($('#' + tbl).css('height'));
    var hHead = parseInt($('#' + tbl + ' thead').css('height'));
    var h = hTable - hHead;

    $('#' + tbl + ' tbody').css('height', h);


    var thead = parseInt($('#' + tbl + ' thead').css('width'));
    var tbody = parseInt($('#' + tbl + ' tbody').css('width'));
    var delta = tbody - thead;

    $('tbody').css('width', $('thead').css('width'));
    var pos = $('tbody').position();
    var left = pos.left;
    var top = pos.top;

    $('tbody').css('left', left - 0);
    $('tbody').css('top', top - 0);

    var colCount = $('#' + tbl + ' thead tr:nth-child(1) th').length;
    var rowCount = $('#' + tbl + ' tbody tr').length;

    for (x = 1; x <= colCount; x++) {
        var w = parseInt($('#' + tbl + ' thead tr:nth-child(1) th:nth-child(' + x + ')').css('width'));
        if (x == colCount) {
            w = w - 18;
        }

        for (y = 1; y <= rowCount; y++) {
            var idx = '#' + tbl + ' tbody tr:nth-child(' + y + ') td:nth-child(' + x + ')';
            $(idx).css('width', w);
        }
    }
}



