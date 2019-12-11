package com.rahmanaulia.latihanrecyclerview

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val image: Int? = null,
    val title: String? = null,
    val desc: String? = null
): Parcelable