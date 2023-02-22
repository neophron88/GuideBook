package com.neophron88.database.upcoming

import com.neophron88.database.upcoming.models.UpcomingEntity

interface UpcomingLocalDataSource {

    suspend fun insertAll(upcomingList: List<UpcomingEntity>)

    suspend fun insertAfterDeleteAll(upcomingList: List<UpcomingEntity>)

    suspend fun fetchUpcomingList(loadSize: Int, offset: Int): List<UpcomingEntity>

    suspend fun fetchUpcoming(upcomingId: Long): UpcomingEntity

}