package com.example.mystartup.ui.map

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mystartup.R
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback

class MapFragment(val cafeActivity: CafeActivity) : Fragment(), OnMapReadyCallback {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LIFECYCLE", "MAPFRAGMENT onCreateView")
        val view = inflater.inflate(R.layout.google_map, container, false)

        return view
    }
    fun jodaehyeon(){

    }

    override fun onMapReady(p0: NaverMap) {
        Log.d("LIFECYCLE", "MAPFRAGMENT onMapReady")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        Log.d("LIFECYCLE", "MAPFRAGMENT onActivityCreated")
        //val sendMsg=arguments?.getString("jebalttt")
        //Log.d("geocoding",sendMsg)
        for (i in 0 until cafeActivity.cafeList.size)
            Log.d("geocoding","뭐야 : "+cafeActivity.cafeList.size.toString()+cafeActivity.cafeList[i].longitude.toString())

        super.onActivityCreated(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("LIFECYCLE", "MAPFRAGMENT onViewCreated")
    }


    override fun onStart() {
        super.onStart()
        Log.d("LIFECYCLE", "MAPFRAGMENT onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LIFECYCLE", "MAPFRAGMENT onStop")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LIFECYCLE", "MAPFRAGMENT onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LIFECYCLE", "MAPFRAGMENT onPause")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.d("LIFECYCLE", "MAPFRAGMENT onLowMemory")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LIFECYCLE", "MAPFRAGMENT onDestroy")
    }


}
