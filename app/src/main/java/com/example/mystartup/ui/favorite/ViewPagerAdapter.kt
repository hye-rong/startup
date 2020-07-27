package com.example.mystartup.ui.favorite

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystartup.R

class ViewPagerAdapter (
    var context: Context,
    var tabTitle:ArrayList<String>
): RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    var favoriteList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.favorite_page, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return tabTitle.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //bind
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var recyclerView_favorite: RecyclerView
        var infoText: TextView
        var isDataText:TextView
        init{
            recyclerView_favorite = itemView.findViewById(R.id.recyclerView_favorite)
            infoText = itemView.findViewById(R.id.infoText)
            isDataText = itemView.findViewById(R.id.isDataText)
        }
    }



}