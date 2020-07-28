package com.example.mystartup.ui.job


import android.os.AsyncTask
import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class JobAsynctask : AsyncTask<Any?, Any?, Any?>() {
    override fun doInBackground(vararg params: Any?): Any? {
        val urlString="http://openapi.seoul.go.kr:8088/77634563776a646831333078745a5578/json/GetJobInfo/1/10/"
        val url=URL(urlString)
        val connection: HttpURLConnection=url.openConnection() as HttpURLConnection

        connection.requestMethod="GET"

        Log.d("async","되나?")

        connection.setRequestProperty("Content-Type", "application/json")
        Log.d("async", "" + connection.responseCode)
        var buffer = ""
//        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
//
//            Log.d("connn", "inputstream : " + connection.inputStream)
//            val reader = BufferedReader(
//                InputStreamReader(
//                    connection.inputStream,
//                    "UTF-8"
//                )
//            )
//            buffer = reader.readLine()
//            Log.d("connn", "buffer : " + buffer)
//            data = Gson().fromJson(buffer, Array<Person>::class.java)
//
//        }

        return null
    }

}