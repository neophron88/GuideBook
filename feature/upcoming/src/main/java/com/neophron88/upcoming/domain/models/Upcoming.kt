package com.neophron88.upcoming.domain.models

data class Upcoming(
    val id: Long,
    val url: String,
    val iconUrl: String,
    val startDate: String,
    val endDate: String,
    val name: String
)
