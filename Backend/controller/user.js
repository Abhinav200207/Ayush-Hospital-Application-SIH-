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

// update controller