package com.example.mystartup.ui.map

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystartup.R
import kotlinx.android.synthetic.main.cafe_list.view.*

class CafeRecyclerAdapter(
    private val cafeActivity: CafeActivity,
    private val inflater: LayoutInflater,
    private val list: ArrayList<CafeInfoServer>
) : RecyclerView.Adapter<CafeRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cafeImage: ImageView
        var cafeName: TextView
        var cafeSimpleInfo: TextView
        var cafeDistrict: TextView

        init {
            cafeImage = itemView.findViewById(R.id.cafe_img)
            cafeName = itemView.findViewById(R.id.cafe_name)
            cafeSimpleInfo = itemView.findViewById(R.id.cafe_info)
            cafeDistrict = itemView.findViewById(R.id.cafe_district)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(cafeActivity)
            .load(list.get(position).FILE_NM)
            .fitCenter()
            .into(holder.cafeImage)
        holder.cafeName.setText( list.get(position).CAFE_NM )
        holder.cafeSimpleInfo.setText( list.get(position).SMPL_INTRO )
        holder.cafeDistrict.setText( list.get(position).GUGUN)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.cafe_list, parent, false)
        return ViewHolder(view)
    }
}