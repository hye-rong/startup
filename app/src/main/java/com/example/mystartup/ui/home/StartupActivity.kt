package com.example.mystartup.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystartup.R
import com.example.mystartup.StartupInfo
import kotlinx.android.synthetic.main.activity_job.*
import kotlinx.android.synthetic.main.activity_startup.*

class StartupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_startup)

        val infoList = intent.getSerializableExtra("startup") as ArrayList<StartupInfo>
        Log.d("sizee",""+infoList.size)

        val adapter = StartupAdapter(infoList, this@StartupActivity)
        startup_recycler_view.adapter = adapter

        startup_recycler_view.layoutManager = LinearLayoutManager(this@StartupActivity)

    }
}