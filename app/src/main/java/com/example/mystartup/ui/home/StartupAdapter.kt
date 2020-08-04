package com.example.mystartup.ui.home

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystartup.R
import com.example.mystartup.StartupInfo


class StartupAdapter(
    val itemList:ArrayList<StartupInfo>,
    val context: StartupActivity
): RecyclerView.Adapter<StartupAdapter.ViewHolder>(){

    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val support: TextView
        val title: TextView
        val target: TextView
        val endDate: TextView

        init{
            support= itemView.findViewById(R.id.startup_support_type)
            title= itemView.findViewById(R.id.startup_title)
            target= itemView.findViewById(R.id.startup_target)
            endDate= itemView.findViewById(R.id.startup_end_date)

            itemView.setOnClickListener {
                val position = adapterPosition
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(itemList[position].detailUrl))
                context.startActivity(intent)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.startup_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.support.setText(itemList.get(position).supportType)
        holder.title.setText(itemList.get(position).title)
        holder.target.setText(itemList.get(position).postTarget)
        holder.endDate.setText(itemList.get(position).endDate)

    }


}
