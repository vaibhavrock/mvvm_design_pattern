package com.example.mvvm_design_pattern.network

import com.example.mvvm_design_pattern.model.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("2.3/users")
    suspend fun getUserList(
        @Query("pagesize") pageSize: Int?,
        @Query("order") order: String?,
        @Query("sort") sort: String?,
        @Query("site") site: String
    ): ApiResponse
}