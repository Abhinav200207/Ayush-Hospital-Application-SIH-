const Hospital = require("../model/hospital_model");

exports.getInfoByUser = async (req, res) => {
    try {
        console.log(req.query.name);
        const hospital = await Hospital.find({ name: { $regex: req.query.name } });
        res.status(200).json({
            success: true,
            hospital
        });
    } catch (error) {
        console.log(error);
    }

}

exports.postHospitalInfo = async (req, res) => {
    try {
        const hospital = await Hospital.create(req.body);
        res.status(200).json({
            success: true,
            hospital
        })
    } catch (error) {
        console.log(error);
    }
}

exports.updateHospitalInfo = async (req, res) => {
    const { name ,hospitalID , bedCount , criticalBed , oxygenCylinderCount  , bloodTypeAvailability } = req.body
    
    try {
        const searchedHospital = await Hospital.findOne({hospitalID:hospitalID})
        const id = searchedHospital._id
        await Hospital.updateOne( {_id:id} , {
            $set:{
                name ,hospitalID , bedCount , criticalBed , oxygenCylinderCount  , bloodTypeAvailability
            }
        });
        res.status(200).json({message:"update succesfully"})
    } catch (error) {
        res.status(404).json({error:"something went wrong"})
    }
    
}

// exports.LoginIn = async(req,res)=>{
//     const { hospitalID } = req.body
//     if(!hospitalID){
//         res.status().json({error:"please enter the Email of respective Hospital"})
//     }
//     try {
        
//     const findHospital = await Hospital.findOne({hospitalID:hospitalID})

//     if(!findHospital){
//         res.status(400).json({error:"Invalid Email"})
//     }
//     else{
//          res.status(400).json({error:"Login Succesfully"})
//     }

//     } catch (error) {
//         console.log(error)
//     }
    
// }

// exports.logoutUser = async (req,res) => {

// }