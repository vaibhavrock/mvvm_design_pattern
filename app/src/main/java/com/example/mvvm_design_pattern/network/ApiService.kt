package com.example.mvvm_design_pattern.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
        //@GET("/v2/venues/search")
        @GET("/v3/places/search")
        fun searchVenues(
            @Query(ApiConstants.PARAM_NEAR) near: String,
            @Query(ApiConstants.PARAM_RADUIS) radius: Int,
            @Query(ApiConstants.PARAM_LIMIT) limit: Int
        ): Call<Any>

        @GET("/v2/venues/{id}")
        fun getDetails(@Path("id") venueId: String): Call<Any>
}