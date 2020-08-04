package com.example.mystartup.ui.home



import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.mystartup.MainActivity
import com.example.mystartup.R
import com.example.mystartup.ui.job.JobActivity
import kotlinx.android.synthetic.main.fragment_home.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class HomeAsyncTask(
    val mainActivity: MainActivity
) : AsyncTask<Any?, Any?, Any?>() {

    lateinit var buffer:String


    override fun doInBackground(vararg params: Any?): Any? {
        val urlString="http://openapi.kised.or.kr/openapi/service/rest/ContentsService/getAnnouncementList?serviceKey=FkHh1kM622aVGEkUpE1P%2BT%2BuyADEBQNOyHyzouzowo0HE1YRi1esgTgfGc8Wmk%2Bf858BSbrcoVYzh%2BGMAlfxtg%3D%3D&pageNo=1&numOfRows=30&pageSize=999&startPage=1&startDate=20190101&endDate=20201020&_type=json"
        val url=URL(urlString)
        val connection: HttpURLConnection=url.openConnection() as HttpURLConnection




        connection.requestMethod="GET"

        Log.d("homee","되나?")

        connection.setRequestProperty("Content-Type", "application/json")
        Log.d("homee", "" + connection.responseCode.toString())

        if (connection.responseCode == HttpURLConnection.HTTP_OK) {

            Log.d("homee", "inputstream : " + connection.inputStream)
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()

            Log.d("homee", "buffer : " + buffer)
            //data = Gson().fromJson(buffer, Array<Person>::class.java)

        }

        return null
    }

    override fun onPostExecute(result: Any?) {
        super.onPostExecute(result)
        mainActivity.infoList = ArrayList()


        val json = JSONObject(buffer)
        val chiefObject = (json["response"] as JSONObject)
        val resultObject = chiefObject.getJSONObject("header")

        if(resultObject.getString("resultCode") == "00") {

            val itemsObject = chiefObject.getJSONObject("body")
            val itemObject = itemsObject.getJSONObject("items")
            val upperArray : JSONArray = itemObject.getJSONArray("item")

            for(i in 0 until upperArray.length()){
                val upperObject = upperArray.getJSONObject(i)



                if(!(upperObject.has("areaname")&&upperObject.getString("areaname").equals("서울특별시")))
                    continue

                Log.d("homee",""+i)

                mainActivity.infoList.add(StartupInfo(

                    upperObject.getString("areaname"),
                    upperObject.getString("detailurl"),
                    upperObject.getString("enddate"),
                    upperObject.getString("posttarget"),
                    upperObject.getString("supporttype"),
                    upperObject.getString("title")

                ))
            }

            val progress_view=mainActivity.findViewById<TextView>(R.id.progress)
            progress_view.visibility = View.GONE
            val pageAdapter = HomePageAdapter(
                LayoutInflater.from(mainActivity),
                mainActivity.infoList
            )
            mainActivity.home_view_pager.adapter = pageAdapter

        }


    }
}