const express = require('express');
const { getInfoByUser, postHospitalInfo } = require('../controller/user');
const router = express.Router();

router.route("/user").get(getInfoByUser);
router.route("/createData").post(postHospitalInfo);
// update router

module.exports = router;
