var apiModel = require('../models/api_model');

module.exports.controller = function(httpApp){

    httpApp.get('/api/getValues/:param/:number/:hex', function(request, response){

        var paramsObject = request.params.param;
        var numberObject = request.params.number;
        var hexObject = request.params.hex;

        try{
            response.setHeader('Content-Type', 'application/json');
            apiModel.getValues(paramsObject, numberObject, hexObject, function(results){
                response.send(results);
            });
        }
        catch (exception){
            response.send(exception + '');
        }
    });
};
