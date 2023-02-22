package com.neophron88.network.upcoming.models

import com.squareup.moshi.Json

class UpcomingResponse(
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "icon")
    val iconUrl: String,
    @field:Json(name = "startDate")
    val startDate: String,
    @field:Json(name = "endDate")
    val endDate: String,
    @field:Json(name = "name")
    val name: String
)
