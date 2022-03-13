const Hospital = require("../model/hospital_model");
const jwt = require("jsonwebtoken");

const options = {
    expires: new Date(
        Date.now() + 1 * 24 * 60 * 60 * 1000
    ),
    httpOnly: true,
};


exports.hospitalLoginPage = (req, res) => {
    res.render("index");
}


exports.getInfoByUser = async (req, res) => {
    try {
        const hospital = await Hospital.find();
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
    const { name, hospitalID, bedCount, criticalBed, oxygenCylinderCount, bloodTypeAvailability } = req.body

    try {
        const searchedHospital = await Hospital.findOne({ hospitalID: hospitalID })
        const id = searchedHospital._id
        await Hospital.updateOne({ _id: id }, {
            $set: {
                name, hospitalID, bedCount, criticalBed, oxygenCylinderCount, bloodTypeAvailability
            }
        });
        res.status(200).json({ message: "update succesfully" })
    } catch (error) {
        res.status(404).json({ error: "something went wrong" })
    }

}

exports.LoginInHospital = async (req, res) => {
    const { hospitalID, password } = req.body
    if (hospitalID == null || password == null) {
        res.status().json({ error: "please enter the HospitalID, password of respective Hospital" })
    }
    try {
        const hospital = await Hospital.findOne({ hospitalID: hospitalID })

        if (hospital == null) {
            res.status(400).json({ error: "Invalid Email" })
        }
        const accessToken = jwt.sign({ hospitalInfo: hospital }, "processxxkjsvbdk454163envJWT_SECRETdgsjkggln", { expiresIn: "12h" });
        res.cookie("accessToken", accessToken, options).json({
            success: true,
            accessToken,
        });

    } catch (error) {
        console.log(error)
    }

}

exports.logoutUser = async (req, res) => {
    res.cookie("accessToken", null, {
        expires: new Date(Date.now()),
        httpOnly: true,
    });

    res.status(200).json({
        success: true,
        message: "Logged Out",
    });
}