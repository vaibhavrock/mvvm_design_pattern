package com.example.mvvm_design_pattern.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "collective")
data class Collective(
    @Embedded
    val collective: CollectiveX,
    val role: String
)