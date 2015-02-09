angular.module('color-palatte-gen')
    .factory('ajaxFactory', function($http) {

            return {
                getColors : function(number, hex){
                    return $http.get('/api/getValues/colors/' + number + '/' + hex +'/')
                                .then(function(result){
                                    return result.data;
                                });
                }
            };
    });
