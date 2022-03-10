package com.example.mvvm_design_pattern.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_design_pattern.repository.UserRepository

class MyViewModelFactory(private val mApplication: Application, private val repo: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel(repo, mApplication) as T
    }
}