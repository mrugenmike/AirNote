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
    $scope.skipCounter=0;
    $scope.totalCount = 0;

    $scope.create = function() {
        //$scope.msg = 'clicked';
        var title = document.getElementById("createTitle").value;
        var contents = document.getElementById("createContent").value;
        console.log('Clicked!!');

        if(title=="" && contents=="")
        {
            $scope.errorMessage = "Please enter note title and contents!";
        }
        else if(title==""){
            $scope.errorMessage = "Note title can not be blank!";
        }
        else if(contents=="")
        {
            $scope.errorMessage = "Note content can not be blank!";
        }
        else
        {
            notesAPIservice.createNote(title, contents, accessToken, userId).success(function (response) {
                $scope.noteId = response.noteId;
                console.log('note id is' + $scope.noteId);
            });

            location.reload();
        }
               //var title = document.getElementById("createTitle").value;
        //var contents = document.getElementById("createContent").value;
    }

    $scope.prevClicked = function(){
        console.log('prev was clicked!');
        if($scope.skipCounter < 10 )
        {
            //document.getElementById("createTitle");
            console.log("Prev click disable!");
        }
        else
        {
            $scope.skipCounter = $scope.skipCounter - 10;
            notesAPIservice.listNotes(accessToken, userId, $scope.skipCounter).success(function (response) {
                $scope.notesList = response.notes;
                $scope.totalCount = response.totalNotes;
                var notesDecorators = [];
                var myNote = null;
                $scope.notesList.forEach(function (note) {
                    console.log(note.title);
                    myNote = new DecoratedNote(note);
                    notesDecorators.push(myNote);
                });
                console.log(notesDecorators.length);
                $scope.noteDecoratorList = notesDecorators;
            }).error(function (data) {
                $scope.notesList = []
            });
        }

    }

    $scope.nextClicked =  function(){
        console.log('next was clicked!!');
        console.log("total count is"+$scope.totalCount);
        console.log("total skip count is"+$scope.skipCounter);
        if(($scope.totalCount - $scope.skipCounter) < 10){
            console.log("Next click disable!");

        }
        else
        {
            $scope.skipCounter = $scope.skipCounter + 10;
            notesAPIservice.listNotes(accessToken, userId, $scope.skipCounter).success(function (response) {
                $scope.notesList = response.notes;
                $scope.totalCount = response.totalNotes;
                var notesDecorators = [];
                var myNote = null;
                $scope.notesList.forEach(function (note) {
                    console.log(note.title);
                    myNote = new DecoratedNote(note);
                    notesDecorators.push(myNote);
                });
                console.log(notesDecorators.length);
                $scope.noteDecoratorList = notesDecorators;
            }).error(function (data) {
                $scope.notesList = []
            });
        }


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

    notesAPIservice.listNotes(accessToken, userId, 0).success(function (response) {
        $scope.notesList = response.notes;
        $scope.totalCount = response.totalNotes;
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

