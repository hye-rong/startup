package com.example.mystartup.ui.job

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.mystartup.R
import kotlinx.android.synthetic.main.activity_job_item.*

class JobItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_item)

        val click_info:JobActivity.JobInfoForList =
            intent.getSerializableExtra("click") as JobActivity.JobInfoForList

        item_CMPNY_NM.setText(click_info.CMPNY_NM)
        item_BSNS_SUMRY_CN.setText(click_info.BSNS_SUMRY_CN)
        //구인정보
        item_JOBCODE_NM.setText(click_info.JOBCODE_NM)
        item_RCRIT_NMPR_CO.setText(click_info.RCRIT_NMPR_CO.toString())
        item_ACDMCR_NM.setText(click_info.ACDMCR_NM)
        item_EMPLYM_STLE_CMMN_MM.setText(click_info.EMPLYM_STLE_CMMN_MM)
        item_WORK_PARAR_BASS_ADRES_CN.setText(click_info.WORK_PARAR_BASS_ADRES_CN)
        item_SUBWAY_NM.setText(click_info.SUBWAY_NM)
        item_DTY_CN.setText(click_info.DTY_CN)
        item_CAREER_CND_NM.setText(click_info.CAREER_CND_NM)
        //근로조건
        item_HOPE_WAGE.setText(click_info.HOPE_WAGE)
        item_RET_GRANTS_NM.setText(click_info.RET_GRANTS_NM)
        item_WORK_TIME_NM.setText(click_info.WORK_TIME_NM)
        item_WORK_TM_NM.setText(click_info.WORK_TM_NM)
        item_HOLIDAY_NM.setText(click_info.HOLIDAY_NM)
        item_WEEK_WORK_HR.setText(click_info.WEEK_WORK_HR)
        item_JO_FEINSR_SBSCRB_NM.setText(click_info.JO_FEINSR_SBSCRB_NM)
        //전형사항
        item_RCEPT_CLOS_NM.setText(click_info.RCEPT_CLOS_NM)
        item_RCEPT_MTH_IEM_NM.setText(click_info.RCEPT_MTH_IEM_NM)
        item_MODEL_MTH_NM.setText(click_info.MODEL_MTH_NM)
        item_RCEPT_MTH_NM.setText(click_info.RCEPT_MTH_NM)
        item_PRESENTN_PAPERS_NM.setText(click_info.PRESENTN_PAPERS_NM)
        //채용 담당자
        item_MNGR_NM.setText(click_info.MNGR_NM)
        item_MNGR_PHON_NO.setText(click_info.MNGR_PHON_NO)
        item_MNGR_INSTT_NM.setText(click_info.MNGR_INSTT_NM)
        item_BASS_ADRES_CN.setText(click_info.BASS_ADRES_CN)





    }
}