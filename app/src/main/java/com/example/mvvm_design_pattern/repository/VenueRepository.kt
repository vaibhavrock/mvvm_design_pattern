package com.example.mvvm_design_pattern.repository

import androidx.lifecycle.MutableLiveData
import com.example.mvvm_design_pattern.MyApplication
import com.example.mvvm_design_pattern.model.ApiResponse
import com.example.mvvm_design_pattern.network.ApiConstants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenueRepository {
    val userResponseLiveData: MutableLiveData<ApiResponse?> = MutableLiveData()
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
                    venueListData.postValue("Error Found!")
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
                    venueDetailsData.postValue("Error Found!")
                }
            })

    }

    suspend fun callUserListAPI(): ApiResponse {

        return MyApplication.apiService.getUserList(20, "desc", "reputation", "stackoverflow")

        //val data = MyApplication.apiService.getUserList(20,"desc","reputation","stackoverflow")

        /* MyApplication.apiService.getUserList(20,"desc","reputation","stackoverflow")
            .enqueue(object : Callback<ApiResponse?>{
                override fun onResponse(
                    call: Call<ApiResponse?>,
                    response: Response<ApiResponse?>
                ) {
                    //print("data: ${response.body()}")
                    userResponseLiveData.postValue(response.body())
                }

                override fun onFailure(call: Call<ApiResponse?>, t: Throwable) {
                    //print("error:")
                    userResponseLiveData.postValue(null)
                }
            })*/

    }


}