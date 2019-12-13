package com.rahmanaulia.aplikasikontak.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Contact(
    var id: Int? = null,
    var name: String? = null,
    var phone: String? = null
): Parcelable