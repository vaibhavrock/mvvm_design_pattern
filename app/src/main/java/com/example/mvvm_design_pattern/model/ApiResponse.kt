package com.example.mvvm_design_pattern.model

data class ApiResponse(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)





