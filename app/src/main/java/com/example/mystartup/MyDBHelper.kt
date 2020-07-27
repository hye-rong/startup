package com.example.mystartup

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(
    val context: Context
) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        val DB_VERSION = 1
        val DB_NAME = "favoritedb.db"
        val TABLE_NAME = "favorite"
    }

    override fun onCreate(p0: SQLiteDatabase?) {

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}