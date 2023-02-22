package com.neophron88.upcoming.domain.repository

import androidx.paging.PagingData
import com.neophron88.feature.result.SingleResult
import com.neophron88.upcoming.domain.models.Upcoming
import kotlinx.coroutines.flow.Flow

interface UpcomingRepository {

    fun getPagedUpcoming(searchBy: String): Flow<PagingData<Upcoming>>

    fun getUpcoming(): SingleResult<Upcoming>

}