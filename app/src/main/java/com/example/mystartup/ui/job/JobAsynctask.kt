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

    override fun doInBackground(vararg params: Any?): Any? {
        val urlString="http://openapi.seoul.go.kr:8088/77634563776a646831333078745a5578/json/GetJobInfo/1/100/"
        val url=URL(urlString)
        val connection: HttpURLConnection=url.openConnection() as HttpURLConnection

        connection.requestMethod="GET"

        Log.d("async","되나?")

        connection.setRequestProperty("Content-Type", "application/json")
        Log.d("async", "" + connection.responseCode.toString())

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {

            Log.d("async", "inputstream : " + connection.inputStream)
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("async", "buffer : " + buffer)
            //data = Gson().fromJson(buffer, Array<Person>::class.java)

        }

        return null
    }

    override fun onPostExecute(result: Any?) {
        super.onPostExecute(result)
        val jobList = ArrayList<JobActivity.JobInfoForList>()

        val json = JSONObject(buffer)
        val chiefObject = (json["GetJobInfo"] as JSONObject)
        val resultObject = chiefObject.getJSONObject("RESULT")

        if(resultObject.getString("CODE") == "INFO-000") {

            val upperArray : JSONArray = chiefObject.getJSONArray("row")
            for(i in 0 until 100){
                val upperObject = upperArray.getJSONObject(i)
                val name = upperObject.getString("CMPNY_NM")
                val info = upperObject.getString("DTY_CN")
                val money = upperObject.getString("HOPE_WAGE")
                val shortMoney = money.split("원")
                val place = upperObject.getString("WORK_PARAR_BASS_ADRES_CN")
                val career = upperObject.getString("CAREER_CND_NM")

                jobList.add(JobActivity.JobInfoForList(name,info,shortMoney[0]+"원",place,career))
            }

            val adapter = RecyclerViewAdapter(jobList, LayoutInflater.from(jobActivity))
            jobActivity.job_recyclerview.adapter = adapter
            jobActivity.job_recyclerview.layoutManager = LinearLayoutManager(jobActivity)

        }


    }
}