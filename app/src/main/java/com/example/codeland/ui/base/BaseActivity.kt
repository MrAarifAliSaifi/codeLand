package com.example.codeland.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM : BaseViewModel, VB : ViewBinding> : AppCompatActivity() {

    private lateinit var binding: VB

    private lateinit var viewModel: VM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = provideViewBinding()
        viewModel = ViewModelProvider(this)[getViewModelClass()]
        setContentView(binding.root)
        setupView(savedInstanceState)
        viewModel.onCreate()
    }

    fun showMessage(message: String) {
        Toast.makeText(this, "Toast", Toast.LENGTH_SHORT).show()
    }
    fun showNoConnectivity() {
        // TODO: Show No Internet Connectivity
    }

    protected abstract fun provideViewBinding(): VB

    @LayoutRes
    protected abstract fun provideLayoutId(): Int
    protected abstract fun providePageName(): String
    protected abstract fun setupView(savedInstanceState: Bundle?)
    protected abstract fun getViewModelClass(): Class<VM>
}