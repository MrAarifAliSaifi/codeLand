package com.example.codeland.ui.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    companion object {
        const val TAG = "BaseViewModel"
    }

    abstract fun onCreate()
}