// var Bridge = require('./clrs_gen/bridge.js');
// var Neighbor = require('./clrs_gen/neighbor.js');
var Palette = require('./clrs_gen/palette');

// var colorContent = {number: 5,
                    // hex: "123456"};

module.exports.getValues = function(encodedParamsObject, encodedNumberObject, encodedHexObject, callback){

    var paramsObject = decodeURI(encodedParamsObject);
    var numberObject = decodeURI(encodedNumberObject);
    var hexObject = decodeURI(encodedHexObject);

    if(paramsObject == "colors"){

        var clrs = new Palette();

        // var hex = String(hexObject);

        clrs.setBase(hexObject);
        clrs.setSteps(numberObject);
        clrs.setHarshness(45);
        // clrs.nextColors();
        callback(clrs.nextColors());
        // callback();
    }
    else{
        callback(null);
    }
};
