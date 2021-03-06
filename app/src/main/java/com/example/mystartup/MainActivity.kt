package com.example.mystartup

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mystartup.ui.educate_money.EducateActivity
import com.example.mystartup.ui.educate_money.MoneyActivity
import com.example.mystartup.ui.favorite.FavoriteActivity
import com.example.mystartup.ui.home.HomeAsyncTask
import com.example.mystartup.ui.home.StartupActivity
import com.example.mystartup.ui.job.JobActivity
import com.example.mystartup.ui.map.CafeActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    lateinit var infoList:ArrayList<StartupInfo>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {

        val asyncTask = HomeAsyncTask(this@MainActivity)
        asyncTask.execute()

        //set toolbar
        setSupportActionBar(toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게


        //****************homefragment code******************//


        main_place.setOnClickListener {
            intent = Intent(this, CafeActivity::class.java)
            startActivity(intent)
        }
        main_job.setOnClickListener {
            intent=Intent(this, JobActivity::class.java)
            startActivity(intent)
        }
        main_money.setOnClickListener {
            intent=Intent(this,MoneyActivity::class.java)
            startActivity(intent)
        }

        main_edu.setOnClickListener {
            intent=Intent(this,EducateActivity::class.java)
            startActivity(intent)
        }




        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_plus_btn.setOnClickListener {
            //더보기 버튼 클릭
            val intent = Intent(this, StartupActivity::class.java)
            intent.putExtra("startup",infoList)
            startActivity(intent)

        }

        home_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(home_tab_layout))

        home_tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                home_view_pager.currentItem = tab!!.position
            }
        })
        home_view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(home_tab_layout))


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_settings->{ // 메뉴 버튼
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }


}


class StartupInfo(var areaName:String, var detailUrl:String, var endDate:String,
                  var postTarget:String, var supportType:String, var title:String):Serializable