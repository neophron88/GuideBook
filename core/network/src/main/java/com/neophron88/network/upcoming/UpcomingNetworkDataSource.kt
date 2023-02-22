package com.neophron88.network.upcoming

import com.neophron88.network.upcoming.models.UpcomingResponse

interface UpcomingNetworkDataSource {

    suspend fun loadAllUpcoming(loadSize: Int,  offset: Int): List<UpcomingResponse>

}