const mongoose = require("mongoose");

const hospitalSchema = new mongoose.Schema({
    name: {
        type: String,
        required: [true, "Name required"],
    },
    hospitalId: {
        type: String,
        unique: true,
        required: [true, "Id required"],
    },
    password: {
        type: String,
        required: [true, "Password required"],
        select: false,
    },
    bedCount: {
        type: Number,
        required: [true, "Bed count required"],
    },
    criticalBed: {
        type: Number,
        required: [true, "Critical Bed count required"],
    },
    oxygencylinderCount: {
        type: Number,
        required: [true, "Oxygen cylinder Count count required"],
    },
    bloodAvailability: [
        {
            bloodname: {
                type: String,
                required: [true, "Name required"],
            },
            numberofunits: {
                type: Number,
                required: [true, "Count required"],
            }
        }
    ],
    longitude: {
        type: String,
        required: [true, "longitude required"],
    },
    latitude: {
        type: String,
        required: [true, "latitude required"],
    }
});

module.exports = mongoose.model("Hospital",hospitalSchema);