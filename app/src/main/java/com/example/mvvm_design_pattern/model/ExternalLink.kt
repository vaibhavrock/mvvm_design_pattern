package com.example.mvvm_design_pattern.model

import androidx.room.Entity

@Entity(tableName = "externalLink")
data class ExternalLink(
    val link: String,
    val type: String
)