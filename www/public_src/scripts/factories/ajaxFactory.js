angular.module('color-palatte-gen')
    .factory('ajaxFactory', function($http) {

            return {
                getColors : function(){
                    return $http.get('/api/getValues/colors')
                                .then(function(result){
                                    return result.data;
                                });
                }
            };
    });
