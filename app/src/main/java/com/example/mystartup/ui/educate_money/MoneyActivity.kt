package com.example.mystartup.ui.educate_money

import android.app.ProgressDialog
import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystartup.R
import com.example.mystartup.data.PublicData
import kotlinx.android.synthetic.main.activity_money.*
import org.jsoup.Jsoup

class MoneyActivity : AppCompatActivity() {

    var dataList= arrayListOf<PublicData>()
    var dataSearch= arrayListOf<PublicData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money)
        init()
    }

    private fun init(){
        MyAsyncTask().execute()

        //검색버튼
        money_find_enter.setOnClickListener {
            money_edit.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
        }
        money_edit.setOnEditorActionListener { v, actionId, event ->
            when(actionId){
                EditorInfo.IME_ACTION_SEARCH -> {
                    //클릭
                    dataSearch.clear()
                    val text = v.text.toString().trim()
                    for(i in 0 until dataList.size){
                        if(dataList[i].name.contains(text) ||dataList[i].institution.contains(text) ) {
                            dataSearch.add(dataList[i])
                        }
                        else{
                            Log.d("addd","NOT contain")
                        }
                    }
                    val adapter = MyPublicAdapter(dataSearch, this@MoneyActivity)
                    money_recyclerview.adapter = adapter
                    Toast.makeText(this,"setOnEditorActionListener", Toast.LENGTH_SHORT).show()
                    CloseKeyboard()
                    true
                }
                else ->{
                    Toast.makeText(this,"뭐징", Toast.LENGTH_SHORT).show()
                    false
                }
            }

        }

        money_edit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                money_edit.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
                Log.d("codee","내가 원하는 enter action")
                true
            }
            else {
                Log.d("codee", "원하지 않는 enter action")
                false
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

    fun getPageInfo(pageIndex:Int,pageSize:Int):String{
        return "&pageIndex"+pageIndex.toString()+"&pageSize="+pageSize.toString()
    }

    inner class MyAsyncTask: AsyncTask<String, String, String>(){

        lateinit var progressDialog : ProgressDialog
        val url1 = "http://api.korea.go.kr/openapi/svc/list?format=xml&serviceKey=P5pThUxFlhq9WgbVUacTrkmFQEQLzEOKjyHIzVLgAPHRuc79fx7W1abgUdFWgzZQXhJG7EIPCVveFHxNyW3bEA%3D%3D&smallAstCd=010203"
        val url2 = "http://api.korea.go.kr/openapi/svc/list?format=xml&serviceKey=P5pThUxFlhq9WgbVUacTrkmFQEQLzEOKjyHIzVLgAPHRuc79fx7W1abgUdFWgzZQXhJG7EIPCVveFHxNyW3bEA%3D%3D&smallAstCd=020101"
        var totalCount1=0
        var totalCount2=0
        var moneyList= arrayListOf<PublicData>()

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog(this@MoneyActivity)
            progressDialog.show()
            progressDialog.setMessage("정보를 불러오는 중입니다..")
        }

        override fun doInBackground(vararg params: String?): String {
            val doc = Jsoup.connect(url1).get()

            //전체 데이터 개수
            totalCount1 = doc.select("totalCount").text().toInt()

            //데이터 전체 doc
            val result_doc = Jsoup.connect(url1+getPageInfo(1,totalCount1)).get()

            //각 데이터 item
            val svcIdList = result_doc.select("svc")
            for(item in svcIdList){
                val itemId = item.select("svcId").text()
                val itemName = item.select("svcNm").text()
                val itemInstitution = item.select("jrsdDptAllNm").text()
                val itemType =item.select("sportFr").text()
                moneyList.add(PublicData(itemId,itemName,itemInstitution,itemType))
            }

            val doc2 = Jsoup.connect(url2).get()

            //전체 데이터 개수
            totalCount2 = doc2.select("totalCount").text().toInt()

            //데이터 전체 doc
            val result_doc2 = Jsoup.connect(url2+getPageInfo(1,totalCount2)).get()

            //각 데이터 item
            val svcIdList2 = result_doc2.select("svc")
            for(item in svcIdList2){
                val itemId = item.select("svcId").text()
                val itemName = item.select("svcNm").text()
                val itemInstitution = item.select("jrsdDptAllNm").text()
                val itemType =item.select("sportFr").text()
                moneyList.add(PublicData(itemId,itemName,itemInstitution,itemType))
            }
            dataList=moneyList

            return doc.title()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog.dismiss()
            money_recyclerview.layoutManager = LinearLayoutManager(applicationContext,
                RecyclerView.VERTICAL,false)
            var adapter =
                MyPublicAdapter(
                    moneyList,
                    applicationContext
                )
            money_recyclerview.adapter = adapter
        }
    }

}