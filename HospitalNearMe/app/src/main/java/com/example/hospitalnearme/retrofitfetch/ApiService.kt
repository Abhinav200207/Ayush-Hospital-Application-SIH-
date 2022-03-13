package com.example.hospitalnearme.retrofitfetch

import com.example.hospitalnearme.hospitalnearme.HospitalData
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/user")
    suspend fun getHospital() : Response<HospitalData>

}