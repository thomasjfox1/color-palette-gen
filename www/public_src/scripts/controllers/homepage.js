angular.module('color-palatte-gen')
    .controller('HomepageCtrl', function($scope, ajaxFactory) {

        $scope.headerSize = null;
        $scope.subTitleSize = null;
        $scope.titleBGColor = '#555';

        $scope.colorInfo = {number: 5, hex: "000000"};

        ajaxFactory.getColors($scope.colorInfo.number, $scope.colorInfo.hex).then(function(results){
            $scope.hexValues = results;
            sizing($scope.colorInfo.number);
            console.log(results);
        });

        $scope.changeColor = function(color){
          ajaxFactory.getColors($scope.colorInfo.number, $scope.colorInfo.hex).then(function(results){
              $scope.hexValues = results;
              sizing($scope.colorInfo.number);
              console.log(results);
          });
        };

        function sizing(numberOfColors){
          $scope.size = 100 / numberOfColors;
        }

    });
