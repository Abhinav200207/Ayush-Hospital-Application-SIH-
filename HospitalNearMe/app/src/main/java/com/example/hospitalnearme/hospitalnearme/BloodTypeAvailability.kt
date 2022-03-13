package com.example.hospitalnearme.hospitalnearme

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class BloodTypeAvailability {
    @SerializedName("bloodName")
    @Expose
    var bloodName: String? = null

    @SerializedName("_id")
    @Expose
    var id: String? = null
}