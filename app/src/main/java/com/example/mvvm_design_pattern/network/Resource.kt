package com.example.mvvm_design_pattern.network

import com.example.mvvm_design_pattern.model.ApiResponse

sealed class Resource {
    data class Success(val data: ApiResponse) : Resource()
    data class Error(val message: String) : Resource()
    object Loading : Resource()
}