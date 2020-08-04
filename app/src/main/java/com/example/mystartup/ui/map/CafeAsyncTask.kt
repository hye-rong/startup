package com.example.mystartup.ui.map

import android.location.Address
import android.location.Geocoder
import android.os.AsyncTask
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.except_map_fragment.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class CafeAsyncTask(
    private val cafeActivity: CafeActivity,
    private val mapFragment: MapFragment
) : AsyncTask<Any?, Any?, Any?>() {
    private lateinit var buffer: String
    //private val cafeList = ArrayList<CafeInfoServer>()
    private lateinit var geoCoingAsync: GeoCoingAsync

    override fun onPostExecute(result: Any?) {
        Log.d("LIFECYCLE", "CafeAsyncTask onPostExecute")
        super.onPostExecute(result)
        val json = JSONObject(buffer)
        val chiefObject = json["jobCafeOpenInfo"] as JSONObject
        val resultArray = chiefObject["RESULT"] as JSONObject
        val resultCode = resultArray.get("CODE").toString()

        if (resultCode == "INFO-000") {
            val realArray = chiefObject["row"] as JSONArray
            /*카페이미지주소(33)FILE_NM
            카페명(1)CAFE_NM
            간략소개(2)SMPL_INTRO
            구군(27)GUGUN*/
            for (i in 0 until realArray.length()) {
                var cafeAddress = realArray.getJSONObject(i).get("BASS_ADRES_CN").toString()
                cafeActivity.cafeList.add(
                    CafeInfoServer(
                        realArray.getJSONObject(i).get("CAFE_NM").toString(),
                        realArray.getJSONObject(i).get("SMPL_INTRO").toString(),
                        realArray.getJSONObject(i).get("SPACE_INFRO").toString(),
                        realArray.getJSONObject(i).get("USE_DT").toString(),
                        realArray.getJSONObject(i).get("HOLI_DD").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO1").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO2").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO3").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO4").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO5").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO6").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO7").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO8").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO9").toString(),
                        realArray.getJSONObject(i).get("FACLT_INFO10").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST1").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST2").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST3").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST4").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST5").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST6").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST7").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST8").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST9").toString(),
                        realArray.getJSONObject(i).get("RSRV_SGGST10").toString(),
                        cafeAddress,
                        realArray.getJSONObject(i).get("GUGUN").toString(),
                        realArray.getJSONObject(i).get("ROAD_ADRES2_CN").toString(),
                        realArray.getJSONObject(i).get("FILE_NM").toString()
                    )
                )
                geoCoingAsync = GeoCoingAsync(
                    cafeActivity,
                    i,
                    realArray.length(),
                    mapFragment
                )
                geoCoingAsync.execute()
            }

        } else {
            //통신잘안됨

        }
        with(cafeActivity.cafe_recycler_view) {
            this.adapter = CafeRecyclerAdapter(
                cafeActivity,
                LayoutInflater.from(cafeActivity),
                cafeActivity.cafeList
            )
            this.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(cafeActivity)
        }
    }

    override fun doInBackground(vararg params: Any?): Any? {
        Log.d("LIFECYCLE", "CafeAsyncTask doInBackground")
        Log.d("requestProperty", "실행됨")
        val urlString =
            "http://openapi.seoul.go.kr:8088/75624659416a64683131306d686f4a6c/json/jobCafeOpenInfo/1/80/"

        val url = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-type", "application/json")
        Log.d("requestProperty1", connection.requestProperties.toString())


        Log.d("requestProperty2", connection.responseCode.toString())
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("requestProperty1", buffer)
        }
        return buffer
    }

}
