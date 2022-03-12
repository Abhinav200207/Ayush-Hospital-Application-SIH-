const mongoose = require("mongoose");

const hospitalSchema = new mongoose.Schema({
    name:{
        type:"String",
        required:[true , "name Required2"]
    },
    hospitalID:{
        type:"String",
        unique:true,
        require:[true , "Email Required"]
    },
    password:{
        type:"String",
        required:true
    },
    bedCount :{
        type :"Number",
        required:[true , "Bed count required"]
    },
    criticalBed:{
        type:"Number",
        requred:[true , "Critical count bed required"]
    },
    oxygenCylinderCount:{
        type:"Number",
        require:[true , "Oxygen Count Required"]
    },
    bloodTypeAvailability:[
        {
            bloodName: {
               type:"String",
               required:[true , "Please enter type of blood Available"]
           }
        }
    ],
    latitude:{
        type:"Number",
        required:true
    },
    longitude:{
        type:"Number",
        required:true
    }

})

module.exports = mongoose.model("Hospital",hospitalSchema);