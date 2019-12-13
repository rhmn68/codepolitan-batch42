package com.rahmanaulia.aplikasikontak.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.ID
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.TABLE_NAME
import java.sql.SQLException

class ContactHelper(context: Context){

    companion object{
        private const val DATABASE_TABLE_CONTACT = TABLE_NAME
        private lateinit var databaseHelper: DatabaseHelper
        private var INSTANCE: ContactHelper? = null

        private lateinit var database: SQLiteDatabase

        fun getInstance(context: Context): ContactHelper{
            if (INSTANCE == null){
                synchronized(SQLiteOpenHelper::class){
                    if (INSTANCE == null){
                        INSTANCE = ContactHelper(context)
                    }
                }
            }
            return INSTANCE as ContactHelper
        }
    }

    init {
        databaseHelper = DatabaseHelper(context)
    }

    @Throws(SQLException::class)
    fun open(){
        database = databaseHelper.writableDatabase
    }

    fun close(){
        databaseHelper.close()

        if (database.isOpen){
            database.close()
        }
    }

    fun queryAll(): Cursor{
        return database.query(
            DATABASE_TABLE_CONTACT,
            null,
            null,
            null,
            null,
            null,
            "$ID ASC",
            null
        )
    }

    fun insert(values: ContentValues) : Long{
        return database.insert(DATABASE_TABLE_CONTACT, null, values)
    }

    fun update(id: String, values: ContentValues): Int{
        return database.update(
            DATABASE_TABLE_CONTACT,
            values,
            "$ID = ?",
            arrayOf(id))
    }

    fun deleteById(id: String): Int{
        return database.delete(DATABASE_TABLE_CONTACT, "$ID = $id", null)
    }
}