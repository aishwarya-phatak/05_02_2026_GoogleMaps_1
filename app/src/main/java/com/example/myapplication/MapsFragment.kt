package com.example.myapplication

import android.Manifest
import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresPermission

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.Polygon
import com.google.android.gms.maps.model.PolygonOptions
import com.google.android.gms.maps.model.Polyline
import com.google.android.gms.maps.model.PolylineOptions

class MapsFragment : Fragment() {

    private lateinit var gMap: GoogleMap
    private lateinit var puneMarker: Marker
    private lateinit var mumbaiMarker: Marker
    private lateinit var polygon: Polygon
    private lateinit var polyline: Polyline

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->
        gMap = googleMap
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */


        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
        initMapSettings()
        initMarkerSettings()
        addPolygon()
        addPolyline()
        setOnMarkerClickListener()
        setOnMarkerDragListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    @RequiresPermission(allOf = [Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION])
    fun initMapSettings() {
        gMap.isBuildingsEnabled = true
        gMap.isIndoorEnabled = true
        gMap.isTrafficEnabled = true
        gMap.isMyLocationEnabled = true
        gMap.uiSettings.isMapToolbarEnabled = true
        gMap.uiSettings.isCompassEnabled = true
        gMap.uiSettings.isTiltGesturesEnabled = true
        gMap.uiSettings.isZoomControlsEnabled = true
        gMap.uiSettings.isMyLocationButtonEnabled = true
        gMap.uiSettings.isRotateGesturesEnabled = true
        gMap.uiSettings.isScrollGesturesEnabled = true
        gMap.mapType = GoogleMap.MAP_TYPE_NORMAL
    }

    fun initMarkerSettings() {
//        val cameraPosition = CameraPosition(LatLng(18.5204, 73.8567), 20.0f, 0.0f, 0.0f)
//        gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        puneMarker = gMap.addMarker(
            MarkerOptions()
                .position(LatLng(18.5204, 73.8567))
                .title("Pune")
                .snippet("This is Pune City")
                .zIndex(4.0F)
                .rotation(30.0F)
                .draggable(true)
                .visible(true)
        )!!

//        val iconImage = BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher_background)
        mumbaiMarker = gMap.addMarker(
            MarkerOptions()
                .position(LatLng(19.07598, 72.87766))
                .title("Mumbai")
                .snippet("City of Dreams!!!")
                .visible(true)
                .draggable(true)
                .zIndex(30.0f)
                .rotation(45.0f)
//                .icon(iconImage)
        )!!
    }

    fun addPolygon() {
        polygon = gMap.addPolygon(
            PolygonOptions()
                .visible(true)
                .zIndex(10.0f)
                .fillColor(R.color.pale_yellow)
                .strokeColor(R.color.dark_pink)
                .strokeWidth(5.0f)
                .add(LatLng(23.1793, 75.7849))
                .add(LatLng(21.2514, 81.6296))
                .add(LatLng(17.3850, 78.4867))
                .add(LatLng(18.2540, 73.8567))
        )
    }

    fun addPolyline() {
        polyline = gMap.addPolyline(
            PolylineOptions()
                .color(R.color.blue)
                .clickable(true)
                .width(10.0f)
                .visible(true)
                .add(LatLng(15.4909, 73.8278))
                .add(LatLng(12.9716, 77.5946))
                .add(LatLng(11.9416, 79.8083))
                .add(LatLng(9.9312, 76.2673))
                .add(LatLng(15.4909, 73.8278))
        )
    }

    fun setOnMarkerClickListener() {
        gMap.setOnMarkerClickListener(MyMarkerClickListener())
    }

    inner class MyMarkerClickListener : GoogleMap.OnMarkerClickListener {
        override fun onMarkerClick(marker1: Marker): Boolean {
            Log.e("marker -- ", "${marker1.position.latitude} -- ${marker1.position.longitude}")
            return false
        }
    }

    fun setOnMarkerDragListener() {
        gMap.setOnMarkerDragListener(MyMarkerDragListener())
    }

    inner class MyMarkerDragListener : GoogleMap.OnMarkerDragListener {
        override fun onMarkerDrag(p0: Marker) {
            Log.e("tag", "${p0.position.latitude} -- ${p0.position.longitude}")
        }

        override fun onMarkerDragEnd(p0: Marker) {
            Log.e("tag", "${p0.position.latitude} -- ${p0.position.longitude}")
        }

        override fun onMarkerDragStart(p0: Marker) {
            Log.e("tag", "${p0.position.latitude} -- ${p0.position.longitude}")
        }
    }
}





