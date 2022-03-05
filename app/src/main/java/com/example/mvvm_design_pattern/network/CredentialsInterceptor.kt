package com.example.mvvm_design_pattern.network

import okhttp3.Interceptor
import okhttp3.Response
import com.example.mvvm_design_pattern.network.ApiConstants

class CredentialsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val url = oldRequest.url().newBuilder()
            .addQueryParameter(ApiConstants.PARAM_CLIENT_ID, ApiConstants.CLIENT_ID_VALUE)
            .addQueryParameter(ApiConstants.PARAM_CLIENT_SECRET, ApiConstants.CLIENT_SECRET_VALUE)
            .build()
        val newRequest = oldRequest.newBuilder().url(url).build()
        return chain.proceed(newRequest)
    }
}