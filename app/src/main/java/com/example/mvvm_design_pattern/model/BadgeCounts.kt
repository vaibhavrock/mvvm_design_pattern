package com.example.mvvm_design_pattern.model

import androidx.room.Entity

@Entity(tableName = "badgeCount")
data class BadgeCounts(
    val bronze: Int,
    val gold: Int,
    val silver: Int
)