package com.rahmanaulia.aplikasikontak.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.ID
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.NAME
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.PHONE
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.TABLE_NAME

class DatabaseHelper(context: Context)
    :SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    companion object{
        private const val DATABASE_NAME = "dbcontactapp"
        private const val DATABASE_VERSION = 1

        private const val SQL_CREATE_TABLE_CONTACT = "CREATE TABLE $TABLE_NAME" +
                " ($ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " $NAME TEXT NOT NULL," +
                " $PHONE TEXT NOT NULL)"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(SQL_CREATE_TABLE_CONTACT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}