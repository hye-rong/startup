package com.example.mystartup.ui.map

import android.app.Activity
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import com.example.mystartup.R
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.internal.HTTPRequest
import kotlinx.android.synthetic.main.activity_cafe.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GeoCoingAsync(
    private val cafeActivity: CafeActivity,
    private var index: Int,
    private var listSize: Int,
    private val mapFragment: MapFragment
) :
    AsyncTask<Any?, Any?, Any?>() {

    lateinit var address: String
    override fun onPreExecute() {
        super.onPreExecute()
        address = cafeActivity.cafeList.get(index).BASS_ADRES_CN.toString()
        Log.d("LIFECYCLE", "GeoCoingAsync onPreExecute")
    }

    lateinit var buffer: String
    override fun doInBackground(vararg params: Any?): Any? {
        Log.d("LIFECYCLE", "GeoCoingAsync doInBackground")
        Log.d("geocodingasync", "실행됨")
        val clientId = cafeActivity.getString(R.string.client_id)
        val clientSecret = cafeActivity.getString(R.string.client_secret)
        val urlString =
            "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=$address"
        val url = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
        connection.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
        connection.setRequestProperty("Content-type", "application/json")
        Log.d(
            "geocodingasync",
            connection.responseCode.toString() + "/" + connection.responseMessage.toString()
        )
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("geocodingasync", buffer)
        }
        val json = JSONObject(buffer)
        val checkJSONObject = json["meta"] as JSONObject
        val addressListCount: Int = checkJSONObject.get("count") as Int
        val chiefJSONArray = json["addresses"] as JSONArray
        if (addressListCount != 0) {
            val addressObject = chiefJSONArray.get(0) as JSONObject
            cafeActivity.cafeList[index].latitude = addressObject.get("y").toString()
            cafeActivity.cafeList[index].longitude = addressObject.get("x").toString()
        }
        return null
    }

    override fun onPostExecute(result: Any?) {
        super.onPostExecute(result)
        Log.d("LIFECYCLE", "GeoCoingAsync onPostExecute")
        val json = JSONObject(buffer)
        val checkJSONObject = json["meta"] as JSONObject
        val addressListCount: Int = checkJSONObject.get("count") as Int
        val chiefJSONArray = json["addresses"] as JSONArray
        if (addressListCount != 0) {
            val addressObject = chiefJSONArray.get(0) as JSONObject
            cafeActivity.cafeList[index].latitude = addressObject.get("y").toString()
            cafeActivity.cafeList[index].longitude = addressObject.get("x").toString()
            Log.d(
                "geocoding", addressObject.get("y").toString() +
                        addressObject.get("x").toString()
            )
        } else {
            //제대로 주소 파싱이 되지 않은 경우
            Log.d("geocodingasync", "index : " + index.toString() + "번째 제대로안됨 어혜령때문에.")
            return
        }
        if (index == listSize - 1) {
            //MapFragment로 list객체 전송하는 부분
            /*Log.d("LIFECYCLE", "GeoCoingAsync onPostExecute list 전송 부분")
            val intent = Intent(cafeActivity.baseContext, MapFragment::class.java)
            intent.putExtra("tlqkf", "from geocoding")
            cafeActivity.startActivity(intent)
            cafeActivity.sendCafeList()*/
            //cafeActivity.sendCafeList()
            val fragmentTransaction=cafeActivity.fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.map_fragment,mapFragment).commit()
        } else {
            return
        }
    }
}