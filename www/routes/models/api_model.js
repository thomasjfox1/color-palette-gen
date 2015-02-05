// java = require('java');

// var titleContent = {title: "HEY THERE",
//                     subtitle: "welcome to your premade app!"};

var colorContent = {number: 6,
                    hex: "123456"};

module.exports.getValues = function(encodedParamsObject, callback){

    var paramsObject = decodeURI(encodedParamsObject);

    // if(paramsObject == "title"){
    //
    //     // java.classpath.push("/src/com/tomlomon/ColorGen.class");
    //
    //
    //     // var ColorGen = java.import("com.tomlomon.ColorGen");
    //     // var color = new ColorGen();
    //
    //     // color.setBase("EA8E8B");
    //     // color.setHarshness((float).8);
    //     // color.setSteps((byte)4);
    //     // color.nextColors();
    //     // callback(color.toString());
    //
    //     callback(titleContent);
    // }

    if(paramsObject == "colors"){
        callback(colorContent);
    }
    else{
        callback(null);
    }
};
