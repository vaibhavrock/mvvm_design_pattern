package com.example.mvvm_design_pattern.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_design_pattern.model.ApiResponse
import com.example.mvvm_design_pattern.model.Converter

@Database(entities = [ApiResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class UserDB : RoomDatabase() {
    abstract val userDao: UserDao
}
