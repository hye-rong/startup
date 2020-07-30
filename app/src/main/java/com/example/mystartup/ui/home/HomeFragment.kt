package com.example.mystartup.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.mystartup.MainActivity
import com.example.mystartup.R
import com.example.mystartup.ui.job.JobActivity
import com.example.mystartup.ui.job.JobAsynctask
import com.example.mystartup.ui.map.CafeActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment(val mainActivity: MainActivity) : Fragment() {


    lateinit var intent: Intent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val asyncTask = HomeAsyncTask(mainActivity)
        asyncTask.execute()

        main_place.setOnClickListener {
            intent = Intent(mainActivity, CafeActivity::class.java)
            startActivity(intent)
        }
        main_job.setOnClickListener {
            intent=Intent(mainActivity, JobActivity::class.java)
            startActivity(intent)
        }
        /*main_edu.setOnClickListener { intent=Intent(mainActivity,"class이름")
        startActivity(intent)}

        main_money.setOnClickListener { intent=Intent(mainActivity,"class이름")
            startActivity(intent) }
*/


        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        home_tab_layout.addTab(home_tab_layout.newTab())
        //ArrayList받아오기
//        infoList = ArrayList<StartupInfo>()
//        for(i in 0 until 5){
//            infoList.add(StartupInfo("title"+(i+1), "창업 정보가 출력됩니다."))
//        }
//        pageAdapter = HomePageAdapter(
//            LayoutInflater.from(context),
//            infoList
//        )
//        home_view_pager.adapter = pageAdapter
//
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
}
class StartupInfo(var areaName:String, var detailUrl:String, var endDate:String,
var postTarget:String, var supportType:String, var title:String){

}