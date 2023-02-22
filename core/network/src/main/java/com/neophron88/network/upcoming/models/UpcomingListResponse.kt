package com.neophron88.network.upcoming.models

import com.squareup.moshi.Json

class UpcomingListResponse(
    @field:Json(name = "data")
    val upcomingList: List<UpcomingResponse>
)
