package com.example.codeland.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.example.codeland.R
import com.example.codeland.databinding.SplashBindingBinding
import com.example.codeland.ui.base.BaseActivity
import com.example.codeland.ui.viewmodels.SplashScreenViewModel

class SplashScreen : BaseActivity<SplashScreenViewModel, SplashBindingBinding>() {

    companion object {
        const val TAG = "SplashScreen"
        private const val SPLASH_SCREEN_DELAY = 2000L
    }

    override fun provideViewBinding(): SplashBindingBinding =
        SplashBindingBinding.inflate(layoutInflater)

    override fun provideLayoutId(): Int = R.layout.splash_binding

    override fun providePageName(): String = TAG

    override fun getViewModelClass(): Class<SplashScreenViewModel> {
        return SplashScreenViewModel::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {
       moveToLoginScreen()
        Toast.makeText(this, "This is toast from the system calling of.", Toast.LENGTH_SHORT).show()
    }

    private fun moveToLoginScreen(){
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(LoginScreen.getStartIntent(this))
            finish()
        }, SPLASH_SCREEN_DELAY)
    }
}