// java = require('java');

var titleContent = "Test Test Test";

module.exports.getData = function(encodedParamsObject, callback){

    var paramsObject = decodeURI(encodedParamsObject);

    if(paramsObject == "title"){

        // java.classpath.push("/src/com/tomlomon/ColorGen.class");


        // var ColorGen = java.import("com.tomlomon.ColorGen");
        // var gradient = new ColorGen();

        // javap.classpath.push("./java/ColorGen.class");

        // gradient.setBase("EA8E8B");
        // gradient.setHarshness((float).8);
        // gradient.setSteps((byte)4);
        // gradient.nextColors();

        // callback(ColorGen.toString());
        callback(titleContent);
    }
    else{
        callback(null);
    }
};


// var ArrayList = java.import('java.util.ArrayList');
// var list = new ArrayList();
// list.addSync('item1');
