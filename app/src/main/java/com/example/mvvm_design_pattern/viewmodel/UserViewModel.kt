package com.example.mvvm_design_pattern.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_design_pattern.R
import com.example.mvvm_design_pattern.network.Resource
import com.example.mvvm_design_pattern.repository.UserRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val application: Application
) : ViewModel(){

   private val _usersListLiveData = MutableLiveData<Resource>()
   val usersListLiveData: LiveData<Resource> get() = _usersListLiveData

    private fun getAPIOrder(): String {
        return application.resources.getString(R.string.api_order)
    }

    private fun getAPISort(): String {
        return application.resources.getString(R.string.api_reputation)
    }

    private fun getAPISite(): String {
        return application.resources.getString(R.string.api_site)
    }

    private fun getDefaultError(): String{
        return application.resources.getString(R.string.default_error)
    }

    fun getUserList(pageSize:Int) {
        if (_usersListLiveData.value is Resource.Success) return
        _usersListLiveData.value = Resource.Loading
        viewModelScope.launch {
            delay(1000)
            try {
                _usersListLiveData.value = Resource.Success(userRepository.callUserListAPI(pageSize,getAPIOrder(),getAPISort(),getAPISite()))
            } catch (e: Exception) {
                _usersListLiveData.value = Resource.Error(e.message ?: getDefaultError())
            }
        }

    }

}