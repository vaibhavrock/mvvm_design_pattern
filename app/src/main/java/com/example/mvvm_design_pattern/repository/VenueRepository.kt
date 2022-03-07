package com.example.mvvm_design_pattern.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_design_pattern.MyApplication
import com.example.mvvm_design_pattern.network.ApiConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenueRepository {
    val venueListData: MutableLiveData<Any> = MutableLiveData()
    val venueDetailsData: MutableLiveData<Any> = MutableLiveData()
//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
    fun callVenueListAPI(near: String){
        MyApplication.apiService.searchVenues(near,ApiConstants.RADIUS_VALUE,ApiConstants.LIMIT_VALUE)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(
                    call: Call<Any?>,
                    response: Response<Any?>
                ) {
                    print("data: ${response.body()}")
                    venueListData.postValue(response.body())
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    print("error:")
                    venueListData.postValue(null)
                }
            })
    }

    fun callVenueDetailsAPI(id: String){
        MyApplication.apiService.getDetails(id)
            .enqueue(object : Callback<Any?> {
                override fun onResponse(
                    call: Call<Any?>,
                    response: Response<Any?>
                ) {
                    print("data: ${response.body()}")
                    venueDetailsData.postValue(response.body())
                }

                override fun onFailure(call: Call<Any?>, t: Throwable) {
                    print("error:")
                    venueDetailsData.postValue(null)
                }
            })

    }


}