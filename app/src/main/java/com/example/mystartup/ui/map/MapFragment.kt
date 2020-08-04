package com.example.mystartup.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystartup.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker

class MapFragment(private val cafeActivity: CafeActivity) : Fragment(), OnMapReadyCallback {

    private lateinit var mapView: MapView

    override fun onMapReady(p0: NaverMap) {
        Log.d("LIFECYCLE", "MAPFRAGMENT onMapReady")
        //p0.mapType = NaverMap.MapType.Hybrid
        val markerArray:ArrayList<Marker> = ArrayList()

        for (i in 0 until cafeActivity.cafeList.size) {
            val x= cafeActivity.cafeList[i].latitude?.toDouble()
            val y= cafeActivity.cafeList[i].longitude?.toDouble()
            if(x!=null&&y!=null){
                markerArray.add(Marker(LatLng(x,y)))
                markerArray[i].map=p0
            }
            else{
                markerArray.add(Marker())
                markerArray[i].map=null
            }
        }
        /*val marker=Marker()
        marker.position = LatLng(
            cafeActivity.cafeList[0].latitude!!.toDouble(),
            cafeActivity.cafeList[0].longitude!!.toDouble()
        )
        marker.map = p0

        val cameraUpdate = CameraUpdate.scrollAndZoomTo(
            LatLng(
                cafeActivity.cafeList[0].latitude!!.toDouble(),
                cafeActivity.cafeList[0].longitude!!.toDouble()
            ), 10.0
        )
        p0.moveCamera(cameraUpdate)*/
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
        //val sendMsg=arguments?.getString("jebalttt")
        //Log.d("geocoding",sendMsg)
        for (i in 0 until cafeActivity.cafeList.size)
            Log.d(
                "geocoding",
                "뭐야 : " + cafeActivity.cafeList.size.toString() + cafeActivity.cafeList[i].longitude.toString()
            )
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
