var apiModel = require('../models/api_model');

module.exports.controller = function(httpApp){

    httpApp.get('/api/getValues/:param', function(request, response){

        var paramsObject = request.params.param;

        try{
            response.setHeader('Content-Type', 'application/json');
            apiModel.getValues(paramsObject, function(results){
                response.send(results);
            });
        }
        catch (exception){
            response.send(exception + '');
        }
    });
};
