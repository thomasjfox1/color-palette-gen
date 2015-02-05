angular.module('color-palatte-gen')
    .controller('HomepageCtrl', function($scope, ajaxFactory) {

        $scope.headerSize = null;
        $scope.subTitleSize = null;
        $scope.titleBGColor = '#555';

        // $scope.colorInfo = {number: 5, hex: "000000"};

        ajaxFactory.getColors().then(function(results){
            $scope.colorInfo = results;
            sizing(results.number);
            // $scope.hexValues = ['#002635', '#013440', '#AB1A25', '#D97925', '#123456', '#123123', '#456456', '#789789', '#987987', '#654654'];
            // $scope.hexValues = ['#588C7E', '#F2E394', '#F2AE72', '#D96459', '#8C4646'];
            $scope.hexValues = ['#fffbd4','#ccfbd4','#99fbd4','#66fbd4','#33fbd4'];
            // $scope.hexValues = ['#DEE7CC','#DEB8CC','#DE8ACC','#DE5CCC','#DE2ECC'];
        });

        $scope.changeColor = function(color){
          $scope.titleBGColor = color;
        };

        function sizing(numberOfColors){
          $scope.size = 100 / numberOfColors;
        }

    });
