package com.neophron88.upcoming.domain.usecases

import com.neophron88.feature.result.SingleResult
import com.neophron88.upcoming.domain.models.Upcoming
import com.neophron88.upcoming.domain.repository.UpcomingRepository

class GetUpcomingUseCase(
    private val repository: UpcomingRepository
) {
    suspend operator fun invoke(upcomingId: Long): SingleResult<Upcoming> =
        repository.getUpcoming(upcomingId)
}