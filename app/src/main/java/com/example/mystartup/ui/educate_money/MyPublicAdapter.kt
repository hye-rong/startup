package com.example.mystartup.ui.educate_money
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystartup.R
import com.example.mystartup.data.PublicData

class MyPublicAdapter (val publicList:ArrayList<PublicData>, context: Context)
    :RecyclerView.Adapter<MyPublicAdapter.MyViewHolder>()
{

    inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var name: TextView = itemView.findViewById(R.id.service_educate)
        var institution:TextView = itemView.findViewById(R.id.institution_educate)
        var type:TextView = itemView.findViewById(R.id.type_educate)
        var item:LinearLayout=itemView.findViewById(R.id.item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_educate, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return publicList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text =publicList[position].name
        holder.institution.text =publicList[position].institution
        holder.type.text = publicList[position].type

        holder.item.setOnClickListener {

        }
    }



}
