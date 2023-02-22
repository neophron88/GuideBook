package com.neophron88.upcoming.presentation.screens.detail

import androidx.annotation.StringRes

sealed class UpcomingDetailUiEvent {
    class Message(@StringRes val resId: Int) : UpcomingDetailUiEvent()
}
