package com.example.mystartup.ui.map

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mystartup.R
import kotlinx.android.synthetic.main.activity_cafe.*

class CafeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)

        val mapFragment = MapFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.map_fragment, mapFragment).commit()

        val listAndDetailFragment = ListAndDetailFragment(this)
        fragmentManager.beginTransaction()
            .replace(R.id.list_detail_fragment, listAndDetailFragment).commit()

        val cafeAsyncTask =
            CafeAsyncTask(this@CafeActivity)
        cafeAsyncTask.execute()
        Log.d("request", "tt")



    }

}