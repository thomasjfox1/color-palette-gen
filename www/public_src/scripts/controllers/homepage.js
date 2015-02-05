angular.module('color-palatte-gen')
    .controller('HomepageCtrl', function($scope, ajaxFactory) {

        $scope.headerSize = null;
        $scope.subTitleSize = null;
        $scope.titleBGColor = '#555';

        // $scope.colorInfo = {number: 5, hex: "000000"};

        $scope.colorOptions = ['#002635', '#013440', '#AB1A25', '#D97925'];

        ajaxFactory.getColors().then(function(results){
            $scope.colorInfo = results;
        });

        $scope.changeColor = function(color){
          $scope.titleBGColor = color;
        };

    });
