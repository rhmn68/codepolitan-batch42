package com.rahmanaulia.aplikasikontak.helper

import android.database.Cursor
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.ID
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.NAME
import com.rahmanaulia.aplikasikontak.db.DatabaseContract.ContactColumns.Companion.PHONE
import com.rahmanaulia.aplikasikontak.model.Contact

object MappingHelper {

    fun mapCursorToArrayList(contactCursor: Cursor) : ArrayList<Contact>{
        val contactList = ArrayList<Contact>()
        while (contactCursor.moveToNext()){
            val id = contactCursor.getInt(contactCursor.getColumnIndexOrThrow(ID))
            val name = contactCursor.getString(contactCursor.getColumnIndexOrThrow(NAME))
            val phone = contactCursor.getString(contactCursor.getColumnIndexOrThrow(PHONE))

            contactList.add(Contact(id, name, phone))
        }
        return contactList
    }
}