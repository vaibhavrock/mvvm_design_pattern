package com.example.mvvm_design_pattern.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_design_pattern.MyApplication
import com.example.mvvm_design_pattern.model.ApiResponse

class VenueRepository {
    val userResponseLiveData: MutableLiveData<ApiResponse?> = MutableLiveData()
    //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

    suspend fun callUserListAPI(): ApiResponse {
        return MyApplication.apiService.getUserList(20, "desc", "reputation", "stackoverflow")
    }


}