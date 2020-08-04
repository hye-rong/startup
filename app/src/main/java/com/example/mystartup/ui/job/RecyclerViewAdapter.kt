import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mystartup.R
import com.example.mystartup.ui.job.JobActivity
import com.example.mystartup.ui.job.JobItemActivity
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList


class RecyclerViewAdapter(
    val itemList:ArrayList<JobActivity.JobInfoForList>,
    val context: JobActivity
): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>(){

    //lateinit var searchList: ArrayList<JobActivity.JobInfoForList>
    inner class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
        val jobName: TextView
        val jobInfo: TextView
        val jobMoney: TextView
        val jobPlace: TextView
        val jobCareer: TextView

        init{
            jobName= itemView.findViewById(R.id.job_name)
            jobInfo= itemView.findViewById(R.id.job_info)
            jobMoney= itemView.findViewById(R.id.job_money)
            jobPlace= itemView.findViewById(R.id.job_place)
            jobCareer= itemView.findViewById(R.id.job_career)

            itemView.setOnClickListener {
                //리스트를 누른 경우
                val position = adapterPosition
                val intent: Intent = Intent(context, JobItemActivity::class.java)
                intent.putExtra("click", itemList[position] as Serializable)
                context.startActivity(intent)

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.job_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val hopeWageSplit = itemList.get(position).HOPE_WAGE.split("원")
        //searchList= ArrayList()

        holder.jobName.setText(itemList.get(position).CMPNY_NM)
        holder.jobInfo.setText(itemList.get(position).BSNS_SUMRY_CN)
        holder.jobMoney.setText(hopeWageSplit[0]+"원")
        holder.jobPlace.setText(itemList.get(position).WORK_PARAR_BASS_ADRES_CN)
        holder.jobCareer.setText(itemList.get(position).CAREER_CND_NM)
    }


}


