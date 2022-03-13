const express = require('express');
const { getInfoByUser, postHospitalInfo, updateHospitalInfo, logoutUser, LoginInHospital, hospitalLoginPage } = require('../controller/user');
const { isLoggedIn } = require('../middleware/auth');
const router = express.Router();

router.route("/user").get(getInfoByUser); // App

router.route("/createData").post(postHospitalInfo); // temprory

router.route("/loginPage").get(hospitalLoginPage); // login
router.route("/login").post(LoginInHospital);

router.route("/update").put(isLoggedIn, updateHospitalInfo); // update

router.route("/logout").get(logoutUser); // logout

module.exports = router;