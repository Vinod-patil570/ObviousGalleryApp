package com.example.obviousgalleryapp.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.obviousgalleryapp.model.ImageResponseItem
import com.obvious.galleryapp.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository: ImageRepository
) : ViewModel() {

    var imageResponse = MutableLiveData<List<ImageResponseItem>>()

    /**
     * method to get Images list from data.json file
     */
    fun getImages(context: Context) {
        val response = repository.getJsonStringFromAssets(context)
        imageResponse.postValue(response)
    }

    //save data
    var imageListData: MutableLiveData<List<ImageResponseItem>> =
        MutableLiveData<List<ImageResponseItem>>()
    var imagePositionData: MutableLiveData<Int> = MutableLiveData<Int>()
    var imageItemData: MutableLiveData<ImageResponseItem> = MutableLiveData<ImageResponseItem>()

    fun getImageListData(): List<ImageResponseItem> {
        return imageListData.value!!
    }

    fun setImageListData(list: List<ImageResponseItem>) {
        this.imageListData.value = list
    }

    fun setImagePositionData(pos: Int) {
        this.imagePositionData.value = pos
    }

    fun getImagePositionData(): Int {
        return imagePositionData.value!!
    }
    

}