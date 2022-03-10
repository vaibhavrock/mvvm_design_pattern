package com.example.mvvm_design_pattern.database

import androidx.room.*
import com.example.mvvm_design_pattern.model.ApiResponse

@Dao
interface UserDao {
    @Query("select * from userTable")
    suspend fun getAllUser(): List<ApiResponse?>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(addUser: ApiResponse?)

    @Update
    suspend fun addToFavourite(updateUser: ApiResponse?)

    @Delete
    fun deleteUser(deleteUser: ApiResponse)
}