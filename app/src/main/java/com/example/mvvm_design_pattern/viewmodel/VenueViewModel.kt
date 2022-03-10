package com.example.mvvm_design_pattern.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm_design_pattern.model.ApiResponse
import com.example.mvvm_design_pattern.repository.VenueRepository
import kotlinx.coroutines.launch

class VenueViewModel(application: Application): AndroidViewModel(application) {

    private var userRepository: VenueRepository? = null
    var usersListLiveData:MutableLiveData<ApiResponse?>?  = null

    init {
        userRepository  = VenueRepository()
        usersListLiveData = userRepository?.userResponseLiveData
    }

    fun getUserList() {
        viewModelScope.launch {
            userRepository?.callUserListAPI()?.let {
                usersListLiveData?.postValue(it)
            } ?: run {
                usersListLiveData?.postValue(null)
            }
        }
    }

}