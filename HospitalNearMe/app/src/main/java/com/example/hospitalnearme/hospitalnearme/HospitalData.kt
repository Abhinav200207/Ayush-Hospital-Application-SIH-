package com.example.hospitalnearme.hospitalnearme

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose
import com.example.hospitalnearme.hospitalnearme.Hospital

class HospitalData {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("hospital")
    @Expose
    var hospital: List<Hospital>? = null
}