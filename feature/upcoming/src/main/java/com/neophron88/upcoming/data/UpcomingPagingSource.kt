package com.neophron88.upcoming.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.neophron88.database.upcoming.UpcomingLocalDataSource
import com.neophron88.feature.exceptions.wrapNetworkExceptions
import com.neophron88.feature.result.SingleResult
import com.neophron88.feature.result.TypeException
import com.neophron88.network.base.BaseUrl
import com.neophron88.network.upcoming.UpcomingNetworkDataSource
import com.neophron88.upcoming.data.helper.mapToUpcomingEntityList
import com.neophron88.upcoming.data.helper.mapToUpcomingList
import com.neophron88.upcoming.domain.models.Upcoming


class UpcomingPagingSource(
    private val baseUrl: BaseUrl,
    private val localDataSource: UpcomingLocalDataSource,
    private val networkDataSource: UpcomingNetworkDataSource
) : PagingSource<Int, Upcoming>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Upcoming> {
        val pageIndex = params.key ?: 0
        val result = fetchUpcoming(params.loadSize, pageIndex)
        return when (result) {
            is SingleResult.Success -> {
                val upcomingList = result.value
                LoadResult.Page(
                    data = upcomingList,
                    prevKey = if (pageIndex == 0) null else pageIndex - 1,
                    nextKey = if (upcomingList.size == params.loadSize) pageIndex + 1 else null
                )
            }
            is SingleResult.Error ->
                LoadResult.Error(throwable = TypeException(result.type))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Upcoming>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    private suspend fun fetchUpcoming(loadSize: Int, offset: Int): SingleResult<List<Upcoming>> {
        return if (offset == REFRESH)
            fetchUpcomingAndTryToRevalidateDatabase(loadSize, offset)
        else
            fetchUpcomingAndTryToInsertToDatabase(loadSize, offset)

    }

    private suspend fun fetchUpcomingAndTryToRevalidateDatabase(
        loadSize: Int, offset: Int
    ): SingleResult<List<Upcoming>> {
        val networkResult = wrapNetworkExceptions {
            val networkUpcomingList = networkDataSource.loadAllUpcoming(loadSize, offset)
            localDataSource.insertAfterDeleteAll(networkUpcomingList.mapToUpcomingEntityList(baseUrl))
        }
        return fetchUpcomingFromDatabaseAfterSyncWithNetwork(loadSize, offset, networkResult)
    }

    private suspend fun fetchUpcomingAndTryToInsertToDatabase(
        loadSize: Int, offset: Int
    ): SingleResult<List<Upcoming>> {
        val localUpcomingList = localDataSource.fetchUpcomingList(loadSize, offset)
        return if (localUpcomingList.isEmpty()) {
            val networkResult = wrapNetworkExceptions {
                val networkUpcomingList = networkDataSource.loadAllUpcoming(loadSize, offset)
                localDataSource.insertAll(networkUpcomingList.mapToUpcomingEntityList(baseUrl))
            }
            fetchUpcomingFromDatabaseAfterSyncWithNetwork(loadSize, offset, networkResult)
        } else SingleResult.Success(localUpcomingList.mapToUpcomingList())
    }

    private suspend fun fetchUpcomingFromDatabaseAfterSyncWithNetwork(
        loadSize: Int, offset: Int, networkResult: SingleResult<Unit>
    ): SingleResult<List<Upcoming>> {
        val localUpcomingList = localDataSource.fetchUpcomingList(loadSize, offset)
        return if (localUpcomingList.isEmpty() && networkResult is SingleResult.Error) {
            SingleResult.Error(networkResult.type)
        } else SingleResult.Success(localUpcomingList.mapToUpcomingList())
    }

    companion object {
        const val REFRESH = 0
    }


}