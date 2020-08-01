package com.example.mystartup

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mystartup.ui.favorite.FavoriteActivity
import com.example.mystartup.ui.favorite.FavoriteFragment
import com.example.mystartup.ui.home.HomeFragment
import com.example.mystartup.ui.job.JobAsynctask
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_toolbar.*

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var ft:FragmentTransaction
    lateinit var fm: FragmentManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()


    }

    private fun init() {

        //fragment 생성
        homeFragment = HomeFragment(this@MainActivity)
        favoriteFragment = FavoriteFragment()

        //fragment manager
        fm = supportFragmentManager
        ft = fm.beginTransaction()

        //set homefragment
        ft.replace(R.id.frame_layout,homeFragment).commit()
        bottom_navigation_view.selectedItemId = R.id.navigation_home

        //navigation listener
        bottom_navigation_view.setOnNavigationItemSelectedListener(object:BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when(item.itemId){
                    R.id.navigation_back->{//이전

                    }
                    R.id.navigation_home->{//홈
                        ft = fm.beginTransaction()
                        ft.replace(R.id.frame_layout,homeFragment).commit()
                    }
                    R.id.navigation_favorite->{//즐겨찾기
                        ft = fm.beginTransaction()
                        ft.replace(R.id.frame_layout,favoriteFragment).commit()
                    }
                }
                return true
            }
        })

        //set toolbar
        setSupportActionBar(toolbar) // 툴바를 액티비티의 앱바로 지정
        supportActionBar?.setDisplayHomeAsUpEnabled(false) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게

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