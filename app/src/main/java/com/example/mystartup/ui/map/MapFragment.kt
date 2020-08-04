package com.example.mystartup.ui.map

import android.net.sip.SipSession
import android.os.Bundle
import android.text.style.CharacterStyle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior.setTag
import androidx.fragment.app.Fragment
import com.example.mystartup.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.Overlay

class MapFragment(private val cafeActivity: CafeActivity) : Fragment(), OnMapReadyCallback,Overlay.OnClickListener {

    private lateinit var mapView: MapView
    private lateinit var infoWindowList: ArrayList<InfoWindow>
    private lateinit var infoWindow:InfoWindow
    private val markerArray: ArrayList<Marker> = ArrayList()

    override fun onMapReady(p0: NaverMap) {
        Log.d("LIFECYCLE", "MAPFRAGMENT onMapReady")
        p0.mapType = NaverMap.MapType.Basic

        var address:String?
        var marker=Marker()
        infoWindow= InfoWindow()
        infoWindowList=ArrayList<InfoWindow>()

        for (i in 0 until cafeActivity.cafeList.size) {
            //infoWindowList.add(InfoWindow())//새로
            val x = cafeActivity.cafeList[i].latitude?.toDouble()
            val y = cafeActivity.cafeList[i].longitude?.toDouble()
            address=cafeActivity.cafeList[i].BASS_ADRES_CN.toString()
            if (x != null && y != null) {
                markerArray.add(Marker(LatLng(x, y)))
                markerArray[i].map = p0
            } else {
                markerArray.add(Marker())
                markerArray[i].map = null
            }
            markerArray[i].tag=address

            infoWindow.adapter=object :InfoWindow.DefaultTextAdapter(cafeActivity){
                override fun getText(p0: InfoWindow): CharSequence {
                    return markerArray[i].tag as CharSequence
                }
            }
            val listener=  Overlay.OnClickListener {
                val marker=it as Marker
                if(marker.infoWindow==null)
                    infoWindow.open(marker)
                else
                    infoWindow.close()
                true
            }
            markerArray[i].onClickListener = listener
            Log.d("markertag",markerArray[i].tag.toString())
        }
        val initCameraPosition = CameraUpdate.scrollAndZoomTo(
            LatLng(37.569425, 126.977945), 10.0
        )
        p0.moveCamera(initCameraPosition)


    }


    override fun onClick(p0: Overlay): Boolean {
        val marker=p0 as Marker
        infoWindow.open(marker)
        Log.d("markertag","click")
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LIFECYCLE", "MAPFRAGMENT onCreateView")
        val view = inflater.inflate(R.layout.google_map, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("LIFECYCLE", "MAPFRAGMENT onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.naver_map)
        mapView.onCreate(savedInstanceState)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("LIFECYCLE", "MAPFRAGMENT onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "MAPFRAGMENT onStart")
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        Log.d("LIFECYCLE", "MAPFRAGMENT onStop")
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        Log.d("LIFECYCLE", "MAPFRAGMENT onResume")
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        Log.d("LIFECYCLE", "MAPFRAGMENT onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
        Log.d("LIFECYCLE", "MAPFRAGMENT onLowMemory")
    }

}
