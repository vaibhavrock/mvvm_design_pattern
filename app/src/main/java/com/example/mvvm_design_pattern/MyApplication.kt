package com.example.mvvm_design_pattern

import android.app.Application
import com.example.mvvm_design_pattern.network.ApiConstants
import com.example.mvvm_design_pattern.network.ApiService
import com.example.mvvm_design_pattern.network.CredentialsInterceptor
import com.example.mvvm_design_pattern.network.VersionInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication: Application() {

    companion object{
        lateinit var apiService: ApiService
    }

    init {
     /*   val client: OkHttpClient = OkHttpClient
            .Builder()
            .build()*/

        val okHttpClient = OkHttpClient()
            .newBuilder()
            .addInterceptor(CredentialsInterceptor())
            .addInterceptor(VersionInterceptor())
            .build()


        apiService= Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}