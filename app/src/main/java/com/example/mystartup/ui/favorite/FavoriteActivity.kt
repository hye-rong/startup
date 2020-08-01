package com.example.mystartup.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mystartup.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_favorite.tabLayout
import kotlinx.android.synthetic.main.fragment_favorite.viewpager

class FavoriteActivity : AppCompatActivity() {

    lateinit var adapter: ViewPagerAdapter
    var tabTitle = arrayListOf<String>("채용","공간","교육","자금")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        init()
        initBtn()
    }

    private fun init() {
        adapter = ViewPagerAdapter(
            this,
            tabTitle
        )
        viewpager.adapter = adapter

        TabLayoutMediator(tabLayout, viewpager, object : TabLayoutMediator.TabConfigurationStrategy{
            override fun onConfigureTab(tab: TabLayout.Tab, position: Int) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                tab.setText(tabTitle[position])
            }
        }).attach()
    }

    private fun initBtn(){
        closeBtn.setOnClickListener {
            this.finish()
        }
    }
}