package com.rahmanaulia.aplikasikontak.db

object DatabaseContract {
    class ContactColumns{
        companion object{
            const val TABLE_NAME = "contact"
            const val ID = "_id"
            const val NAME = "name"
            const val PHONE = "phone"
        }
    }
}