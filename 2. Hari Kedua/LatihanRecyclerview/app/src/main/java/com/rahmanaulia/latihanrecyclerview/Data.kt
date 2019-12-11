package com.rahmanaulia.latihanrecyclerview

import android.content.Context

class Data (context: Context){

    private val dataTitle = context.resources.getStringArray(R.array.data_title_movie)
    private val dataDesc = context.resources.getStringArray(R.array.data_desc_movie)
    private val dataImage = context.resources.obtainTypedArray(R.array.data_image_movie)

    fun dataMovie(): List<Movie>{
        val list = ArrayList<Movie>()
        for (i in dataTitle.indices){
            list.add(
                Movie(
                dataImage.getResourceId(i, 0),
                    dataTitle[i],
                    dataDesc[i]
            ))
        }
        return list
    }

}