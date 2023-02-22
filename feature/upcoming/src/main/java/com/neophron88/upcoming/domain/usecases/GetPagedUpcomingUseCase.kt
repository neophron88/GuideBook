package com.neophron88.upcoming.domain.usecases

import androidx.paging.PagingData
import com.neophron88.upcoming.domain.models.Upcoming
import com.neophron88.upcoming.domain.repository.UpcomingRepository
import kotlinx.coroutines.flow.Flow

class GetPagedUpcomingUseCase(
    private val repository: UpcomingRepository
) {
    operator fun invoke(): Flow<PagingData<Upcoming>> =
        repository.getPagedUpcoming()
}