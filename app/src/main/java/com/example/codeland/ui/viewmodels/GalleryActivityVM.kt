package com.example.codeland.ui.viewmodels

import android.graphics.Bitmap
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codeland.ui.base.BaseViewModel

class GalleryActivityVM : BaseViewModel() {


    private val _selectedImage = MutableLiveData<Bitmap>()
    val selectedImage: LiveData<Bitmap> get() = _selectedImage
    fun setSelectedImage(bitmap: Bitmap) {
        _selectedImage.value = bitmap
    }

    override fun onCreate() {}
}