package com.example.mystartup.ui.job

import RecyclerViewAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystartup.R
import kotlinx.android.synthetic.main.activity_job.*

class JobActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job)

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
