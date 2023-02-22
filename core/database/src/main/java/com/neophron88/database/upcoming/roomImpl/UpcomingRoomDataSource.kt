package com.neophron88.database.upcoming.roomImpl

import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.database.upcoming.models.UpcomingEntity

class UpcomingRoomDataSource(
    private val upcomingDao: UpcomingDao
) : UpcomingLocalDataSource {

    override suspend fun insertAll(upcomingList: List<UpcomingEntity>) =
        upcomingDao.insertAll(upcomingList)

    override suspend fun insertAfterDeleteAll(upcomingList: List<UpcomingEntity>) {
        upcomingDao.insertAfterDeleteAll(upcomingList)
    }

    override suspend fun fetchUpcomingList(loadSize: Int, offset: Int): List<UpcomingEntity> {
        return upcomingDao.fetchUpcomingList(loadSize, offset * loadSize)
    }

    override suspend fun fetchUpcoming(upcomingId: Long): UpcomingEntity =
        upcomingDao.fetchUpcoming(upcomingId)

}