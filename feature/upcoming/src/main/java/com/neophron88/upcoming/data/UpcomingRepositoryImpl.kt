package com.neophron88.upcoming.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.feature.exceptions.wrapDatabaseExceptions
import com.neophron88.feature.result.SingleResult
import com.neophron88.feature.result.map
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.upcoming.data.helper.mapToUpcoming
import com.neophron88.upcoming.domain.models.Upcoming
import com.neophron88.upcoming.domain.repository.UpcomingRepository
import kotlinx.coroutines.flow.Flow

class UpcomingRepositoryImpl(
    private val localDataSource: UpcomingLocalDataSource,
    private val remoteDataSource: UpcomingNetworkDataSource
) : UpcomingRepository {


    override fun getPagedUpcoming(): Flow<PagingData<Upcoming>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UpcomingPagingSource(localDataSource, remoteDataSource) }
        ).flow

    }

    override suspend fun getUpcoming(upcomingId: Long): SingleResult<Upcoming> {
        val result = wrapDatabaseExceptions { localDataSource.fetchUpcoming(upcomingId) }
        return result.map { it.mapToUpcoming() }
    }

    companion object {
        private const val PAGE_SIZE = 3
    }
}