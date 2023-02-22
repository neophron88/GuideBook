package com.neophron88.network.upcoming.retrofitImpl

import com.neophron88.network.upcoming.models.UpcomingListResponse
import retrofit2.http.GET

interface UpcomingService {

    @GET("service/v2/upcomingGuides/")
    suspend fun loadAllUpcoming(): UpcomingListResponse
}