package com.example.mvvm_design_pattern.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_design_pattern.MyApplication
import com.example.mvvm_design_pattern.network.ApiConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenueRepository {
    val userResponseLiveData: MutableLiveData<Any> = MutableLiveData()

    fun callVenueListAPI(near: String){
        MyApplication.apiService.searchVenues(near,ApiConstants.PARAM_RADUIS,ApiConstants.PARAM_LIMIT)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(
                    call: Call<Any?>,
                    response: Response<Any?>
                ) {
                    print("data: ${response.body()}")
                    userResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    print("error:")
                    userResponseLiveData.postValue(null)
                }
            })

    }
}