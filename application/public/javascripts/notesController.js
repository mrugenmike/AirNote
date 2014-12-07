var controller = angular.module('AirNoteApp.controllers', ['ngCookies']);

controller.config(function ($httpProvider) {
    //Enable cross domain calls
    $httpProvider.defaults.useXDomain = true;

    //Remove the header used to identify ajax call  that would prevent CORS from working
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

controller.controller('notesController', function ($scope, notesAPIservice, $cookies, $cookieStore) {
    var userId = $cookies.uid;
    var accessToken = $cookies.accessToken;
    $scope.nameFilter = null;
    $scope.notesList = [];
    $scope.username = null;
    $scope.noteId = null;
    $scope.note = null;
    $scope.currentNoteId=null;

    $scope.create = function() {
        //$scope.msg = 'clicked';
        console.log('Clicked!!');
        var title = document.getElementById("createTitle").value;
        var contents = document.getElementById("createContent").value;

        notesAPIservice.createNote(title, contents, accessToken, userId).success(function (response) {
            $scope.noteId = response.noteId;
            console.log('note id is' + $scope.noteId);
        });
    }

    $scope.update = function() {
        console.log(' update clicked');
        var title = document.getElementById("updateTitle").value;
        var contents = document.getElementById("editContent").value;
        notesAPIservice.updateNote(title,contents,$scope.currentNoteId,accessToken,userId).success(function(response){
          //$scope.noteId = response.noteId;
        });
    }

    $scope.delete = function(){
        console.log('delete clicked!! for '  + $scope.currentNoteId);
        notesAPIservice.deleteNote(accessToken, userId, $scope.currentNoteId).success(function (response) {
            console.log('Deleted');
        });
    }

        notesAPIservice.getUserInfo(accessToken).success(function (response) {
        $scope.username = response.display_name

    }).error(function (data, status, headers, config) {
        $scope.username = "oops we had an error!"
    });

    notesAPIservice.listNotes(accessToken, userId).success(function (response) {
        $scope.notesList = response;
        var notesDecorators = [];
        var myNote = null;
        $scope.notesList.forEach(function (note) {
            console.log(note.title);
            myNote = new DecoratedNote(note);
            //myNote.setCurrentNote();
            notesDecorators.push(myNote);
        });
        console.log(notesDecorators.length);
        $scope.noteDecoratorList = notesDecorators;
    }).error(function (data) {
        $scope.notesList = []
    });

    function DecoratedNote(note) {
        this.note = note;
        this.noteId = note.noteId;
        this.setCurrentNote = function () {
            console.log("Click done at" + note.title);
            $scope.clickedNoteTitle = note.title;
             notesAPIservice.fetchNote(accessToken, userId, this.noteId).success(function (response) {
                //$scope.note = response;
                 $scope.currentNoteContent = response.content;
                 $scope.currentNoteId = response.noteId;
            });
        };
    }
$scope.reload = function(){
    location.reload();
}

    $scope.clear = function() {
        $scope.newNoteTitle = "";
        $scope.newNoteContent = "";};

    });

