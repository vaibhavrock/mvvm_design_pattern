package com.example.mvvm_design_pattern.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.mvvm_design_pattern.model.ApiResponse
import com.example.mvvm_design_pattern.repository.VenueRepository

class VenueViewModel(application: Application): AndroidViewModel(application) {

    var venueListLiveData: LiveData<Any>?  = null
    var venueDetailsLiveData: LiveData<Any>?  = null
    private var userRepository: VenueRepository? = null
    var usersListLiveData:LiveData<ApiResponse>?  = null


    init {
        userRepository  = VenueRepository()
        venueListLiveData = userRepository?.venueListData
        venueDetailsLiveData = userRepository?.venueDetailsData
        usersListLiveData = userRepository?.userResponseLiveData

    }

    fun getVenueList(near:String) {
        userRepository?.callVenueListAPI(near)
    }

    fun getVenueDetails(id:String) {
        userRepository?.callVenueDetailsAPI(id)
    }

    fun getUserList() {
        userRepository?.callUserListAPI()
    }

}