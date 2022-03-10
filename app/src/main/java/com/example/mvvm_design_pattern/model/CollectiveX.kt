package com.example.mvvm_design_pattern.model

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "collectiveX")
data class CollectiveX(
    val description: String,
    @Embedded
    val external_links: List<ExternalLink>,
    val link: String,
    val name: String,
    val slug: String,
    val tags: List<String>
)