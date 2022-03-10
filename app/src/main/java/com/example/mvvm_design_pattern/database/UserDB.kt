package com.example.mvvm_design_pattern.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_design_pattern.model.ApiResponse
import com.example.mvvm_design_pattern.model.Converter

@Database(entities = [ApiResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class UserDB: RoomDatabase() {
    abstract fun getUserDao(): UserDao

    companion object {
        private const val DB_NAME = "user_database.db"
        @Volatile private var instance: UserDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDB::class.java,
            DB_NAME
        ).build()
    }
}