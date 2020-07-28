package com.example.mystartup

import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.mystartup.ui.favorite.FavoriteFragment
import com.example.mystartup.ui.home.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

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
        homeFragment = HomeFragment()
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

    }


}