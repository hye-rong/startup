package com.example.mystartup.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.mystartup.MainActivity
import com.example.mystartup.R
import com.example.mystartup.StartupInfo

class HomePageAdapter(
    val context: MainActivity,
    var infoList:ArrayList<StartupInfo>
): PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = context.layoutInflater.inflate(R.layout.home_info, container, false)
        val titleView = view.findViewById<TextView>(R.id.pager_title)
        val typeView = view.findViewById<TextView>(R.id.pager_support_type)
        val dateView = view.findViewById<TextView>(R.id.pager_end_date)
        val targetView = view.findViewById<TextView>(R.id.pager_target)
        titleView.setText(infoList[position].title)
        typeView.setText(infoList[position].supportType)
        dateView.setText(infoList[position].endDate)
        targetView.setText(infoList[position].postTarget)
        container.addView(view)
        view.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(infoList[position].detailUrl))
            context.startActivity(intent)
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view ===`object` as View
    }

    override fun getCount(): Int {
        return 5
    }

}