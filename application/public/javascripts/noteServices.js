angular.module('AirNoteApp.noteServices', []).
    factory('notesAPIservice', function($http) {

        var notesAPI = {};

        notesAPI.getUserInfo = function() {
            return $http({
                headers: {'Authorization': 'Bearer L5WpAIC9mn0AAAAAAAAAkXl4dATdr26BLATTHl-HSg9Ezn1EXD5K6-_STgilxSv3'},
                url: 'http://localhost:8080/api/users'
            });
        }

        notesAPI.listNotes = function() {
            return $http({
                headers: {'Authorization': 'Bearer L5WpAIC9mn0AAAAAAAAAkXl4dATdr26BLATTHl-HSg9Ezn1EXD5K6-_STgilxSv3'},
                url: 'http://localhost:8080/api/notes/351441185'
            });
        }

        notesAPI.createNote = function(){
            return $http({
                headers: {'Authorization': 'Bearer L5WpAIC9mn0AAAAAAAAAkXl4dATdr26BLATTHl-HSg9Ezn1EXD5K6-_STgilxSv3'},
                url: 'http://localhost:8080/api/notes',
                data: {"title": "Vab",
                    "content": "This is test content!",
                    "userId": "351441185"
                },
                method: 'POST'
            });
        }

        notesAPI.fetchNote = function(){
            return $http({
                headers: {'Authorization': 'Bearer L5WpAIC9mn0AAAAAAAAAkXl4dATdr26BLATTHl-HSg9Ezn1EXD5K6-_STgilxSv3'},
                url: 'http://localhost:8080/api/notes/351441185/54562835a8262eb8960d7f04'
                });
        }

        notesAPI.updateNote = function(){
            return $http({
                headers:
                })
        }

        notesAPI.deleteNote = function(){
            return $http({

            })
        }

        return notesAPI;
    });
