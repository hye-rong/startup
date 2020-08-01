package com.example.mystartup.ui.job

import RecyclerViewAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystartup.R
import kotlinx.android.synthetic.main.activity_job.*

class JobActivity : AppCompatActivity() {

    lateinit var jobList:ArrayList<JobActivity.JobInfoForList>
    lateinit var jobListForSearch:ArrayList<JobActivity.JobInfoForList>
    lateinit var job_edu_list:ArrayList<String>
    lateinit var job_career_list:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

        Log.d("mymymy","onCreate")

        jobList = ArrayList()
        jobListForSearch = ArrayList()
        job_edu_list = ArrayList()
        job_career_list = ArrayList()

        val asyncTask = JobAsynctask(this@JobActivity)
        asyncTask.execute()


        //검색버튼
        jod_find_enter.setOnClickListener {
            job_edit.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }
        job_edit.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> {
                    //클릭
                    Toast.makeText(this,"조대현 멍청이ㅎㅎ",Toast.LENGTH_SHORT).show()
                    CloseKeyboard()
                    true
                }
                else ->{
                    Toast.makeText(this,"뭐징",Toast.LENGTH_SHORT).show()
                    false
                }
            }
        }
        job_edit.setOnKeyListener { v, keyCode, event ->
            when(keyCode){
                KeyEvent.KEYCODE_ENTER->{
                    CloseKeyboard()
                    Toast.makeText(this,"조대현 멍청이ㅎㅎ",Toast.LENGTH_SHORT).show()
                    true
                }
                else->{
                    false
                }
            }
        }

        //상세검색버튼
        job_search_btn.setOnClickListener {
            if(checkbox_view.visibility==View.GONE) {
                checkbox_view.visibility = View.VISIBLE
                job_search_btn.setText("상세조건on")
            }
            else {
                checkbox_view.visibility = View.GONE
                job_search_btn.setText("상세조건off")
            }
        }
        job_detail_search_btn.setOnClickListener {
            //상세검색
            //초기화
            jobListForSearch.clear()
            jobListForSearch = jobList.clone() as ArrayList<JobInfoForList>

            job_edu_list.clear()
            job_career_list.clear()

//            Log.d("mymymy","jobList:"+jobList.size)
//            Log.d("mymymy","first:"+jobListForSearch.size)

            if(!checkbox_edu_1.isChecked){
                //학력이 전체가 아닌 경우
                if(checkbox_edu_2.isChecked)
                    job_edu_list.add("J00102")
                if(checkbox_edu_3.isChecked)
                    job_edu_list.add("J00104")
                if(checkbox_edu_4.isChecked)
                    job_edu_list.add("J00106")
                if(checkbox_edu_5.isChecked)
                    job_edu_list.add("J00108")
                if(checkbox_edu_6.isChecked)
                    job_edu_list.add("J00110")
                if(checkbox_edu_7.isChecked)
                    job_edu_list.add("J00114")

                for(i in jobListForSearch.size-1 downTo 0) {
                    var checkSearch:Boolean = false
                    for (j in 0 until job_edu_list.size) {
                        if (jobListForSearch[i].ACDMCR_CMMN_CODE_SE.equals(job_edu_list[j]))
                        {
                            checkSearch= true
                            break
                        }
                    }
                    if(!checkSearch)
                        jobListForSearch.removeAt(i)

                }

            }
            if(!checkbox_career_1.isChecked){
                //경력이 전체가 아닌 경우
                if(checkbox_career_2.isChecked)
                    job_career_list.add("J01300")
                if(checkbox_career_3.isChecked)
                    job_career_list.add("J01301")
                if(checkbox_career_4.isChecked)
                    job_career_list.add("J01302")

                for(i in jobListForSearch.size-1 downTo 0) {
                    var checkSearch:Boolean = false
                    for (j in 0 until job_career_list.size) {
                        if (jobListForSearch[i].CAREER_CND_CMMN_CODE_SE.equals(job_career_list[j]))
                        {
                            checkSearch= true
                            break
                        }
                    }
                    if(!checkSearch)
                        jobListForSearch.removeAt(i)
                }

            }

            val adapter = RecyclerViewAdapter(jobListForSearch, LayoutInflater.from(this@JobActivity))
            job_recyclerview.adapter = adapter



            checkbox_view.visibility = View.GONE
            job_search_btn.setText("상세조건off")
        }

        //체크박스
        checkbox_edu_1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_2.isChecked=false
                checkbox_edu_3.isChecked=false
                checkbox_edu_4.isChecked=false
                checkbox_edu_5.isChecked=false
                checkbox_edu_6.isChecked=false
                checkbox_edu_7.isChecked=false
            }
        }
        checkbox_edu_2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_1.isChecked = false
            }
        }
        checkbox_edu_3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_1.isChecked = false
            }
        }
        checkbox_edu_4.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_1.isChecked = false
            }
        }
        checkbox_edu_5.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_1.isChecked = false
            }
        }
        checkbox_edu_6.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_1.isChecked = false
            }
        }
        checkbox_edu_7.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_edu_1.isChecked = false
            }
        }

        checkbox_career_1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_career_2.isChecked=false
                checkbox_career_3.isChecked=false
                checkbox_career_4.isChecked=false
            }
        }
        checkbox_career_2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_career_1.isChecked = false
            }
        }
        checkbox_career_3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_career_1.isChecked = false
            }
        }
        checkbox_career_4.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked==true){
                checkbox_career_1.isChecked = false
            }
        }




    }


    fun CloseKeyboard()
    {
        var view = this.currentFocus

        if(view != null)
        {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    class JobInfoForList(
        var JO_REGIST_NO:String,
        var CMPNY_NM:String,
        var BSNS_SUMRY_CN :String,
        var RCRIT_JSSFC_CMMN_CODE_SE:String,
        var JOBCODE_NM:String,
        var RCRIT_NMPR_CO:Int,
        var ACDMCR_CMMN_CODE_SE:String,
        var ACDMCR_NM:String,
        var EMPLYM_STLE_CMMN_CODE_SE:String,
        var EMPLYM_STLE_CMMN_MM:String,
        var WORK_PARAR_BASS_ADRES_CN:String,
        var SUBWAY_NM:String,
        var DTY_CN:String,
        var CAREER_CND_CMMN_CODE_SE:String,
        var CAREER_CND_NM:String,
        var HOPE_WAGE:String,
        var RET_GRANTS_NM:String,
        var WORK_TIME_NM:String,
        var WORK_TM_NM:String,
        var HOLIDAY_NM:String,
        var WEEK_WORK_HR:String,
        var JO_FEINSR_SBSCRB_NM:String,
        var RCEPT_CLOS_NM:String,
        var RCEPT_MTH_IEM_NM:String,
        var MODEL_MTH_NM:String,
        var RCEPT_MTH_NM:String,
        var PRESENTN_PAPERS_NM:String,
        var MNGR_NM:String,
        var MNGR_PHON_NO:String,
        var MNGR_INSTT_NM:String,
        var BASS_ADRES_CN:String
    ) {}
}
