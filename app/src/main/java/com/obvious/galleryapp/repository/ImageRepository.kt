package com.obvious.galleryapp.repository

import android.content.Context
import android.util.Log
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obvious.galleryapp.Utils
import javax.inject.Inject


class ImageRepository @Inject constructor() {

    /**
     * method to get JSON string from data.json file from assets folder
     */
    fun getJsonStringFromAssets(context: Context): List<ImageResponseItem> {
        val jsonFileData = Utils.getJsonFromAssets(context, "data.json")
        Log.i("json_data", jsonFileData)
        return getDataModelFromJsonString(jsonFileData)
    }

    /**
     * method to parse Data class from JSON string using GSON
     */
    private fun getDataModelFromJsonString(jsonStr: String): List<ImageResponseItem> {
        val typeToken = object : TypeToken<List<ImageResponseItem>>() {}.type
        val imageList: List<ImageResponseItem> = Gson().fromJson(jsonStr, typeToken)
        return imageList
    }
}