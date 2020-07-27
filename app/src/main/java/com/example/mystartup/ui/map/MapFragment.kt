package com.example.mystartup.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystartup.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment :Fragment(){
    lateinit var mMap:GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.google_map,container,false)
        /*val mapView=view.findViewById<MapView>(R.id.map)
        mapView.getMapAsync(this)*/
        return inflater.inflate(R.layout.google_map,container,false)
    }

    /*override fun onMapReady(p0: GoogleMap?) {
        mMap = p0!!
        val marker = LatLng(37.502976, 126.980316)
        mMap.addMarker(
            MarkerOptions().position(marker).title("똥작역")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
        )
        //mMap.addMarker(MarkerOptions().position(marker)
        // .title("혜령이똥").icon(BitmapDescriptorFactory.fromResource(R.drawable.common_google_signin_btn_icon_dark)))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15F))
    }*/
}