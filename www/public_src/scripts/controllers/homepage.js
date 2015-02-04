angular.module('wwwApp')
    .controller('HomepageCtrl', function($scope, ajaxFactory) {

        $scope.headerSize = null;
        $scope.subTitleSize = null;
        $scope.titleBGColor = '#555';

        $scope.colorOptions = ['#002635', '#013440', '#AB1A25', '#D97925'];

        ajaxFactory.getAppTitle().then(function(results){
            $scope.mainTitle = results;
        });

        $scope.changeColor = function(color){
          $scope.titleBGColor = color;
        };

    });
