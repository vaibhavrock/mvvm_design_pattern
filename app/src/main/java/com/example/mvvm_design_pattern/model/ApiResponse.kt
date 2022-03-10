package com.example.mvvm_design_pattern.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class ApiResponse(
    @PrimaryKey(autoGenerate = true)
    var userID: Int = 0,
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)





