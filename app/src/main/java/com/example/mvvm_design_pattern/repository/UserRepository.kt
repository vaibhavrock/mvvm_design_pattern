package com.example.mvvm_design_pattern.repository

import com.example.mvvm_design_pattern.MyApplication
import com.example.mvvm_design_pattern.model.ApiResponse

class UserRepository {
    suspend fun callUserListAPI(pageSize:Int, apiOrder: String, apiSort: String, apiSite: String): ApiResponse {
        return MyApplication.apiService.getUserList(pageSize, apiOrder, apiSort, apiSite)
    }

}