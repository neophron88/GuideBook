package com.neophron88.network.upcoming.retrofitImpl

import com.neophron88.network.base.wrapRetrofitExceptions
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.network.upcoming.models.UpcomingResponse

class UpcomingRetrofitDataSource(
    private val service: UpcomingService
) : UpcomingNetworkDataSource {

    override suspend fun loadAllUpcoming(loadSize: Int, offset: Int): List<UpcomingResponse> =
        wrapRetrofitExceptions {
            val list = service.loadAllUpcoming().upcomingList
            val fromIndex = offset * loadSize
            val toIndex = fromIndex + loadSize

            if (fromIndex < 0 || fromIndex >= list.size) emptyList()
            else if (toIndex < 0) emptyList()
            else if (toIndex >= list.size) list.subList(fromIndex, list.size)
            else list.subList(fromIndex, toIndex)
        }

}