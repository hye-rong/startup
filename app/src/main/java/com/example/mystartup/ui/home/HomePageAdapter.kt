package com.example.mystartup.ui.home

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.mystartup.R

class HomePageAdapter(
    val layoutInflater: LayoutInflater,
    var infoList:ArrayList<StartupInfo>
): PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = layoutInflater.inflate(R.layout.home_info, container, false)
        val titleView = view.findViewById<TextView>(R.id.pager_title)
        val msgView = view.findViewById<TextView>(R.id.pager_msg)
        titleView.setText(infoList[position].title)
        msgView.setText(infoList[position].msg)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view ===`object` as View
    }

    override fun getCount(): Int {
        return infoList.size
    }
}