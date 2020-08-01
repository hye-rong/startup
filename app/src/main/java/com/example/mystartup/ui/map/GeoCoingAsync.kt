package com.example.mystartup.ui.map

import android.os.AsyncTask
import android.util.Log
import com.example.mystartup.R
import com.naver.maps.map.internal.HTTPRequest
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class GeoCoingAsync(
    val cafeActivity: CafeActivity,
    val cafeList: ArrayList<CafeInfoServer>,
    var index: Int
) :
    AsyncTask<Any?, Any?, Any?>() {
    lateinit var address: String
    override fun onPreExecute() {
        super.onPreExecute()
        address = cafeList.get(index).BASS_ADRES_CN.toString()
    }

    lateinit var buffer: String
    override fun doInBackground(vararg params: Any?): Any? {
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

        return null
    }

    override fun onPostExecute(result: Any?) {
        super.onPostExecute(result)
        val json = JSONObject(buffer)
        val checkJSONObject = json["meta"] as JSONObject
        val addressListCount: Int = checkJSONObject.get("count") as Int
        val chiefJSONArray = json["addresses"] as JSONArray
        if (addressListCount != 0) {
            val addressObject = chiefJSONArray.get(0) as JSONObject
            cafeList[index].latitude = addressObject.get("y").toString()
            cafeList[index].longitude = addressObject.get("x").toString()
        } else {
            //제대로 주소 파싱이 되지 않은 경우
            Log.d("geocodingasync", "index : " + index.toString() + "번째 제대로안됨 어혜령때문에.")
            return
        }

    }
}