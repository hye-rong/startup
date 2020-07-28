package com.example.mystartup.ui.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystartup.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_cafe.*
import kotlinx.android.synthetic.main.favorite_page.*

class CafeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)
        
        val mapFragment = MapFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.map_fragment, mapFragment)
        fragmentTransaction.commit()

        val cafeList = ArrayList<CafeInfo>()
        for (i in 0 until 20)
            cafeList.add(
                CafeInfo(
                    "http://job.seoul.go.kr/www/common/img.jsp?dir=jobcafe&name=MIN_0559_1.jpg"
                    , "CAFE" + i
                    , "info info info" + i
                    , "강서구" + i
                )
            )

        cafe_recycler_view.adapter = CafeRecyclerAdapter(
            this@CafeActivity,
            LayoutInflater.from(this@CafeActivity),
            cafeList
        )
        cafe_recycler_view.layoutManager = LinearLayoutManager(this@CafeActivity)
    }

}