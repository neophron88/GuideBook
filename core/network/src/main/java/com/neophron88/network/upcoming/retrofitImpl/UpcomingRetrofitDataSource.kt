package com.neophron88.network.upcoming.retrofitImpl

import com.neophron88.network.base.wrapRetrofitExceptions
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.network.upcoming.models.UpcomingQuery
import com.neophron88.network.upcoming.models.UpcomingResponse

class UpcomingRetrofitDataSource(
    private val service: UpcomingService
) : UpcomingNetworkDataSource {

    override suspend fun loadAllUpcoming(query: UpcomingQuery): List<UpcomingResponse> =
        wrapRetrofitExceptions {
            val upcomingList = service.loadAllUpcoming().upcomingList
            val fromIndex = query.offset * query.loadSize
            val toIndex = fromIndex + query.loadSize

            if (fromIndex < 0 || fromIndex >= upcomingList.size) emptyList()
            else if (toIndex < 0 || toIndex >= upcomingList.size) emptyList()
            else upcomingList.subList(fromIndex, toIndex)
        }

}