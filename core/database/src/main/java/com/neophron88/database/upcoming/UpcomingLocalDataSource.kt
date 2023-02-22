package com.neophron88.database.upcoming

import androidx.paging.PagingSource
import com.neophron88.database.upcoming.models.UpcomingEntity

interface UpcomingLocalDataSource {

    fun getPageableUpcoming():PagingSource<Int,UpcomingEntity>
}