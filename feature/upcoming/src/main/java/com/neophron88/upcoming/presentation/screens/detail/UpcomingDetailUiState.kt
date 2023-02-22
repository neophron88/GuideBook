package com.neophron88.upcoming.presentation.screens.detail

import com.neophron88.upcoming.domain.models.Upcoming

data class UpcomingDetailUiState(
    val data: Upcoming? = null,
    val editInProgress: Boolean = false
) {
    val editNotInProgress get() = !editInProgress

}