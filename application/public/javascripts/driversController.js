var controller = angular.module('F1FeederApp.controllers', []);

controller.config(function($httpProvider) {
    //Enable cross domain calls
    $httpProvider.defaults.useXDomain = true;

    //Remove the header used to identify ajax call  that would prevent CORS from working
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

controller.controller('driversController', function($scope, notesAPIservice) {
        $scope.nameFilter = null;
        $scope.driversList = [];

        notesAPIservice.getDrivers().success(function (response) {
            //Dig into the responde to get the relevant data
            //$scope.driversList = response.MRData.StandingsTable.StandingsLists[0].DriverStandings;
            console.log("response is" + response.display_name);
        }).error(function(data, status, headers, config){
            console.log("Error: "+status);
        });
    });

