package com.example.hospitalnearme

import android.Manifest
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.GoogleMap
import android.os.Bundle
import com.google.android.gms.maps.SupportMapFragment
import com.example.hospitalnearme.R
import android.widget.Toast
import com.example.hospitalnearme.MapsActivity
import android.content.pm.PackageManager
import android.os.Looper
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import com.example.hospitalnearme.databinding.ActivityMapsBinding
import com.example.hospitalnearme.retrofitfetch.GetData
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

//import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
class MapsActivity : FragmentActivity(), OnMapReadyCallback {
    private var mMap: GoogleMap? = null
    private var binding: ActivityMapsBinding? = null
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private var lat = 0.0
    private var lng = 0.0
    var find: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(this.applicationContext)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        find = findViewById(R.id.findButton)
        find!!.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "stringBuilder", Toast.LENGTH_SHORT).show()
        })
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        currentLocation

        try {
            CoroutineScope(Dispatchers.Default).launch {
                GetData().getDataHospital(mMap!!)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (Request_code) {
            Request_code -> if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                currentLocation
            }
        }
    }// Toast.makeText(getApplicationContext(), "current location is "+ location.getLongitude(), Toast.LENGTH_SHORT).show();

    //                Toast.makeText(getApplicationContext(), "location result is = "+locationResult, Toast.LENGTH_SHORT).show();
    private val currentLocation: Unit
        get() {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    Request_code
                )
            }
            val locationRequest = LocationRequest.create()
            locationRequest.interval = 60000
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            locationRequest.fastestInterval = 5000
            val locationCallback: LocationCallback = object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
//                Toast.makeText(getApplicationContext(), "location result is = "+locationResult, Toast.LENGTH_SHORT).show();
                    if (locationRequest == null) {
                        Toast.makeText(
                            applicationContext,
                            "current location is null",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    for (location in locationResult.locations) {
                        if (location != null) {
                            // Toast.makeText(getApplicationContext(), "current location is "+ location.getLongitude(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
            fusedLocationProviderClient!!.requestLocationUpdates( locationRequest,
                locationCallback, Looper.getMainLooper())

            val task = fusedLocationProviderClient!!.lastLocation
            task.addOnSuccessListener { location ->
                if (location != null) {
                    lat = location.latitude
                    lng = location.longitude
                    val latLng = LatLng(lat, lng)
                    mMap!!.addMarker(MarkerOptions().position(latLng).title("current location"))
                    mMap!!.moveCamera(CameraUpdateFactory.newLatLng(latLng))
                    mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
                }
            }
        }

    companion object {
        const val Request_code = 101
    }
}