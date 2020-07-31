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
    val cafeActivity: CafeActivity
) :
    AsyncTask<Any?, Any?, Any?>() {
    override fun onPreExecute() {
        super.onPreExecute()

    }

    lateinit var buffer: String
    override fun doInBackground(vararg params: Any?): Any? {
        Log.d("geocodingasync", "실행됨")
        var address: String = "경기도 과천시 별양로 111"
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
        val chiefJSONArray = json["addresses"] as JSONArray
        val realObject = chiefJSONArray.get(0) as JSONObject
        val x = realObject.get("x").toString()
        val y = realObject.get("y").toString()
        Log.d("geocodingasync", chiefJSONArray.toString())
        Log.d("geocodingasync", realObject.toString())
        Log.d("geocodingasync", x)
        Log.d("geocodingasync", y)
    }
}