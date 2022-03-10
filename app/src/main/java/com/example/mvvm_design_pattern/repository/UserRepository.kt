package com.example.mvvm_design_pattern.repository

import com.example.mvvm_design_pattern.MyApplication
import com.example.mvvm_design_pattern.database.UserDB
import com.example.mvvm_design_pattern.model.ApiResponse

class UserRepository(private val userDB: UserDB) {
    suspend fun callUserListAPI(pageSize:Int, apiOrder: String, apiSort: String, apiSite: String): ApiResponse {
        return MyApplication.apiService.getUserList(pageSize, apiOrder, apiSort, apiSite)
    }

    suspend fun getDataFromDb(): ApiResponse? {
        val listApi = userDB.getUserDao().getAllUser()
        return if (listApi != null && listApi.isNotEmpty()) {
            listApi[0]
        } else {
            null
        }
    }

    suspend fun saveData(data: ApiResponse?) {
        userDB.getUserDao().addUser(data)

    }

}