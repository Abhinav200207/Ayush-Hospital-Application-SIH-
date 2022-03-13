package com.example.hospitalnearme.hospitalnearme


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hospital {
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("hospitalID")
    @Expose
    var hospitalID: String? = null

    @SerializedName("password")
    @Expose
    var password: String? = null

    @SerializedName("bedCount")
    @Expose
    var bedCount: Int? = null

    @SerializedName("criticalBed")
    @Expose
    var criticalBed: Int? = null

    @SerializedName("oxygenCylinderCount")
    @Expose
    var oxygenCylinderCount: Int? = null

    @SerializedName("bloodTypeAvailability")
    @Expose
    var bloodTypeAvailability: List<BloodTypeAvailability>? = null

    @SerializedName("latitude")
    @Expose
    var latitude: Double? = null

    @SerializedName("longitude")
    @Expose
    var longitude: Double? = null

    @SerializedName("__v")
    @Expose
    var v: Int? = null
}