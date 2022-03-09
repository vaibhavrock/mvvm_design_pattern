package com.example.mvvm_design_pattern.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm_design_pattern.model.ApiResponse
import com.example.mvvm_design_pattern.repository.VenueRepository
import kotlinx.coroutines.launch

class VenueViewModel(application: Application): AndroidViewModel(application) {

    var venueListLiveData: MutableLiveData<Any>?  = null
    var venueDetailsLiveData: MutableLiveData<Any>?  = null
    private var userRepository: VenueRepository? = null

    var usersListLiveData:MutableLiveData<ApiResponse?>?  = null

    init {
        userRepository  = VenueRepository()
        venueListLiveData = userRepository?.venueListData
        venueDetailsLiveData = userRepository?.venueDetailsData
        usersListLiveData = userRepository?.userResponseLiveData

    }

    fun getVenueList(near:String) {
        viewModelScope.launch {
            userRepository?.callVenueListAPI(near)
        }

    }

    fun getVenueDetails(id:String) {
        viewModelScope.launch {
            userRepository?.callVenueDetailsAPI(id)
        }

    }

    fun getUserList() {
        viewModelScope.launch {
            userRepository?.callUserListAPI()?.let {
                //print("API response $it")
                usersListLiveData?.postValue(it)
            } ?: run {
                usersListLiveData?.postValue(null)
            }

        }

    }

}