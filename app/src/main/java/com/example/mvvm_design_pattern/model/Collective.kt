package com.example.mvvm_design_pattern.model

import androidx.room.Entity

@Entity(tableName = "collective")
data class Collective(
    val collective: CollectiveX,
    val role: String
)