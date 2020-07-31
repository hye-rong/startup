package com.example.mystartup.ui.job


import RecyclerViewAdapter
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_job.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class JobAsynctask(
    val jobActivity: JobActivity
) : AsyncTask<Any?, Any?, Any?>() {

    lateinit var buffer:String
    lateinit var jobList:ArrayList<JobActivity.JobInfoForList>

    override fun doInBackground(vararg params: Any?): Any? {
        val urlString="http://openapi.seoul.go.kr:8088/77634563776a646831333078745a5578/json/GetJobInfo/1/100/"
        val url=URL(urlString)
        val connection: HttpURLConnection=url.openConnection() as HttpURLConnection


        connection.requestMethod="GET"

        Log.d("jobb","되나?")

        connection.setRequestProperty("Content-Type", "application/json")
        Log.d("jobb", "" + connection.responseCode.toString())

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {

            Log.d("jobb", "inputstream : " + connection.inputStream)
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("jobb", "buffer : " + buffer)
            //data = Gson().fromJson(buffer, Array<Person>::class.java)

        }

        return null
    }

    override fun onPostExecute(result: Any?) {
        super.onPostExecute(result)
        jobList = ArrayList()

        val json = JSONObject(buffer)
        val chiefObject = (json["GetJobInfo"] as JSONObject)
        val resultObject = chiefObject.getJSONObject("RESULT")

        if(resultObject.getString("CODE") == "INFO-000") {

            val upperArray : JSONArray = chiefObject.getJSONArray("row")
            //Log.d("jobb",""+upperArray.length())
            for(i in 0 until upperArray.length()){
                val upperObject = upperArray.getJSONObject(i)
//                Log.d("jobb",""+i+")"+
//                        upperObject.getString("RCRIT_JSSFC_CMMN_CODE_SE")+
//                        upperObject.getString("JOBCODE_NM"))


                Log.d("jobbb",upperObject.getString("WORK_PARAR_BASS_ADRES_CN"))

                jobList.add(JobActivity.JobInfoForList(
                    upperObject.getString("JO_REGIST_NO"),
                    upperObject.getString("CMPNY_NM"),
                    upperObject.getString("BSNS_SUMRY_CN"),
                    upperObject.getString("RCRIT_JSSFC_CMMN_CODE_SE"),
                    upperObject.getString("JOBCODE_NM"),
                    upperObject.getInt("RCRIT_NMPR_CO"),
                    upperObject.getString("ACDMCR_CMMN_CODE_SE"),
                    upperObject.getString("ACDMCR_NM"),
                    upperObject.getString("EMPLYM_STLE_CMMN_CODE_SE"),
                    upperObject.getString("EMPLYM_STLE_CMMN_MM"),
                    upperObject.getString("WORK_PARAR_BASS_ADRES_CN"),
                    upperObject.getString("SUBWAY_NM"),
                    upperObject.getString("DTY_CN"),
                    upperObject.getString("CAREER_CND_CMMN_CODE_SE"),
                    upperObject.getString("CAREER_CND_NM"),
                    upperObject.getString("HOPE_WAGE"),
                    upperObject.getString("RET_GRANTS_NM"),
                    upperObject.getString("WORK_TIME_NM"),
                    upperObject.getString("WORK_TM_NM"),
                    upperObject.getString("HOLIDAY_NM"),
                    upperObject.getString("WEEK_WORK_HR"),
                    upperObject.getString("JO_FEINSR_SBSCRB_NM"),
                    upperObject.getString("RCEPT_CLOS_NM"),
                    upperObject.getString("RCEPT_MTH_IEM_NM"),
                    upperObject.getString("MODEL_MTH_NM"),
                    upperObject.getString("RCEPT_MTH_NM"),
                    upperObject.getString("PRESENTN_PAPERS_NM"),
                    upperObject.getString("MNGR_NM"),
                    upperObject.getString("MNGR_PHON_NO"),
                    upperObject.getString("MNGR_INSTT_NM"),
                    upperObject.getString("BASS_ADRES_CN")

                ))
            }



            val adapter = RecyclerViewAdapter(jobList, LayoutInflater.from(jobActivity))
            jobActivity.job_recyclerview.adapter = adapter
            jobActivity.job_recyclerview.layoutManager = LinearLayoutManager(jobActivity)

        }


    }
}