package com.neophron88.network.upcoming

import com.neophron88.network.upcoming.models.UpcomingQuery
import com.neophron88.network.upcoming.models.UpcomingResponse

interface UpcomingNetworkDataSource {

    suspend fun loadAllUpcoming(query: UpcomingQuery): List<UpcomingResponse>

}