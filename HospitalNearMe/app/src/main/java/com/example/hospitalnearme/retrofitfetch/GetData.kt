package com.example.hospitalnearme.retrofitfetch

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GetData {
    suspend fun getDataHospital(mMap : GoogleMap){ // Puchna Hai
        CoroutineScope(Dispatchers.Default).launch {
            val result = RetrofitHelper.getInstance().getHospital().body()!!.hospital!!
            val job1 = launch {
                CoroutineScope(Dispatchers.Main).launch {
                    for (i in result){
                        val lat : Double = i.latitude!!.toDouble()
                        val lng : Double = i.longitude!!.toDouble()

                        val latLng = LatLng(lat, lng)

                        mMap.addMarker(MarkerOptions().position(latLng).title("${i.name.toString()}, B.Cnt: ${i.bedCount.toString()}, C.B.Cnt: ${i.criticalBed.toString()}, O.C.A: ${i.oxygenCylinderCount.toString()}"))
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                    }
                }
            }
        }
    }
}