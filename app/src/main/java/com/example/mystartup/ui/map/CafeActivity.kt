package com.example.mystartup.ui.map

import android.annotation.SuppressLint
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.replace
import com.example.mystartup.R
import kotlinx.android.synthetic.main.activity_cafe.*

open class CafeActivity : AppCompatActivity() {
    val cafeList = ArrayList<CafeInfoServer>()
    val mapFragment = MapFragment(this)
    val fragmentManager = supportFragmentManager
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cafe)

        Log.d("LIFECYCLE", "CafeActivity onCreate")


        val cafeAsyncTask = CafeAsyncTask(this@CafeActivity)
        cafeAsyncTask.execute()
        mapFragment.jodaehyeon()
    }

    /*fun sendCafeList() {
        //Activity에서 Fragment로 보내는 구문
        val bundle: Bundle = Bundle()
        bundle.putString("jebalttt","성공성공성공성공성공성공성공성공")
        mapFragment.arguments = bundle
    }*/
}