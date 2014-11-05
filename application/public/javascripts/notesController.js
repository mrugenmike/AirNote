var controller = angular.module('AirNoteApp.controllers', ['ngCookies']);

controller.config(function($httpProvider) {
    //Enable cross domain calls
    $httpProvider.defaults.useXDomain = true;

    //Remove the header used to identify ajax call  that would prevent CORS from working
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

controller.controller('notesController', function($scope, notesAPIservice,$cookieStore) {
        $scope.nameFilter = null;
        $scope.notesList = [];
        $scope.username=null;
        notesAPIservice.getUserInfo().success(function (response) {
            $scope.username =response.display_name

        }).error(function(data, status, headers, config){
            $scope.username="oops we had an error!"
        });

        notesAPIservice.listNotes().success(function(response){
            $scope.notesList = response;
        });
    });

