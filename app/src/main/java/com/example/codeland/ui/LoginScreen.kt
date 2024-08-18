package com.example.codeland.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.codeland.R
import com.example.codeland.databinding.LoginBindingBinding
import com.example.codeland.ui.base.BaseActivity
import com.example.codeland.ui.viewmodels.LoginScreenViewModel

class LoginScreen : BaseActivity<LoginScreenViewModel, LoginBindingBinding>() {
    companion object {
        const val TAG = "LoginScreen"
        fun getStartIntent(context: Context) = Intent(context, LoginScreen::class.java)
    }

    override fun provideViewBinding(): LoginBindingBinding =
        LoginBindingBinding.inflate(layoutInflater)

    override fun provideLayoutId(): Int = R.layout.login_binding

    override fun providePageName(): String = TAG

    override fun getViewModelClass(): Class<LoginScreenViewModel> {
        return LoginScreenViewModel::class.java
    }

    override fun setupView(savedInstanceState: Bundle?) {
        binding.btnLogin.setOnClickListener {
            setupClickListener()
        }
    }
    private fun setupClickListener() {
        Toast.makeText(this, "Hello is this is the thing which we have to change the.", Toast.LENGTH_SHORT).show()
        val userName = binding.etUserName.text.toString()
        val userPassword = binding.etUserPassword.text.toString()

        if (userName.isEmpty()) {
            binding.outlinedTextFieldUserName.error = getString(R.string.app_name)
        } else {
            binding.outlinedTextFieldUserName.error = null
        }
        if (userPassword.isEmpty()) {
            binding.outlinedTextFieldUserPassword.error = getString(R.string.message_userpassword_empty)
        } else {
            binding.outlinedTextFieldUserPassword.error = null
        }
        if(userPassword.isNotEmpty() && userName.isNotEmpty()){
            moveToGalleryImage()
        }
        Toast.makeText(this@LoginScreen, "This screen is regarding to the hot ", Toast.LENGTH_SHORT).show()
    }
    private  fun moveToGalleryImage(){
        startActivity(GalleryActivity.getStartIntent(this))
        finish()
    }
}
