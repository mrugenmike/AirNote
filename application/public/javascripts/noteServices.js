angular.module('F1FeederApp.noteServices', []).
    factory('notesAPIservice', function($http) {

        var notesAPI = {};

        notesAPI.getDrivers = function() {
            return $http({
                headers: {'Authorization': 'Bearer L5WpAIC9mn0AAAAAAAAAkXl4dATdr26BLATTHl-HSg9Ezn1EXD5K6-_STgilxSv3'},
                url: 'http://localhost:8080/api/users'
            });
        }

        return notesAPI;
    });
