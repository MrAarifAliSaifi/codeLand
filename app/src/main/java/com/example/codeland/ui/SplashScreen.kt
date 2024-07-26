package com.example.codeland.ui

import android.os.Bundle
import com.example.codeland.R
import com.example.codeland.databinding.SplashBindingBinding
import com.example.codeland.ui.base.BaseActivity
import com.example.codeland.ui.viewmodels.SplashScreenViewModel

class SplashScreen : BaseActivity<SplashScreenViewModel, SplashBindingBinding>() {

    companion object {
        const val TAG = "SplashScreen"
    }

    override fun provideViewBinding(): SplashBindingBinding =
        SplashBindingBinding.inflate(layoutInflater)

    override fun provideLayoutId(): Int = R.layout.splash_binding

    override fun providePageName(): String = TAG

    override fun getViewModelClass(): Class<SplashScreenViewModel> {
        return SplashScreenViewModel::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {

    }
}