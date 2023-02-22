package com.neophron88.database.upcoming.roomImpl

import androidx.paging.PagingSource
import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.database.upcoming.models.UpcomingEntity

class UpcomingRoomDataSource(
    private val upcomingDao: UpcomingDao
) : UpcomingLocalDataSource {

    override fun getPageableUpcoming(): PagingSource<Int, UpcomingEntity> =
        upcomingDao.fetchUpcomingList()

}