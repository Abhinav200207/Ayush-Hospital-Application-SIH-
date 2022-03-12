const express = require('express');
const { getInfoByUser, postHospitalInfo } = require('../controller/user');
const router = express.Router();

router.route("/user").get(getInfoByUser);
router.route("/createData").post(postHospitalInfo);
router.route("/update").put(updateHospitalInfo);
// router.route("/login").post(loginIn);
// router.route("/logout").post(lououtUser);

module.exports = router;
