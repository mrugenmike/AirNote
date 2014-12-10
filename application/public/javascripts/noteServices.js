angular.module('AirNoteApp.noteServices', []).
    factory('notesAPIservice', function($http) {

        var notesAPI = {};

        notesAPI.getUserInfo = function(accessToken) {
            return $http({
                headers: {'Authorization': 'Bearer '+ accessToken},
                url: 'http://localhost:8080/api/users'
            });
        }

        notesAPI.listNotes = function(accessToken,userId) {
            return $http({
                headers: {'Authorization': 'Bearer '+ accessToken},
                url: 'http://localhost:8080/api/notes/' + userId
            });
        }

        notesAPI.createNote = function(title, contents, accessToken, userId){
            console.log(title);
            console.log(contents);
            return $http({
                headers: {'Authorization': 'Bearer '+ accessToken},
                url: 'http://localhost:8080/api/notes',
                data: {"title": title,
                    "content": contents,
                    "userId": userId
                },
                method: 'POST'
            });
        }

        notesAPI.fetchNote = function(accessToken, userId, noteId){
            return $http({
                headers: {'Authorization': 'Bearer '+ accessToken},
                url: 'http://localhost:8080/api/notes/'+ userId +'/'+noteId
                //url: 'http://localhost:8080/api/notes/'+ userId +'/54562835a8262eb8960d7f04'
                });
        }

        notesAPI.updateNote = function(title, contents, noteId, accessToken, userId){
            return $http({
                headers: {'Authorization': 'Bearer '+ accessToken},
                url: 'http://localhost:8080/api/notes/'+userId +'/'+ noteId ,
                data: {"title": title,
                    "content": contents
                    //"noteId": "547a7c0c0364648b83bc8170"
                },
                method: 'PUT'
            })
        }

        notesAPI.deleteNote = function(accessToken, userId, noteId){
            console.log('note id' + noteId);
            return $http({
                headers: {'Authorization': 'Bearer '+accessToken},
                url: 'http://localhost:8080/api/notes/'+userId+'/'+ noteId,
                //url: 'http://localhost:8080/api/notes/'+userId+'/54562835a8262eb8960d7f04',
                method: 'DELETE'
            })
        }

        return notesAPI;
    });
