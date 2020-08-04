package com.example.mystartup.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mystartup.R

class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val infoList = intent.getSerializableExtra("startup") as ArrayList<StartupInfo>
        Log.d("startupp", infoList[0].areaName)
    }
}