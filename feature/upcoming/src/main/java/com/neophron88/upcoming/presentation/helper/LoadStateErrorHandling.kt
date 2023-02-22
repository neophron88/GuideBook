package com.neophron88.upcoming.presentation.helper

import androidx.annotation.StringRes
import androidx.paging.LoadState
import com.neophron88.feature.result.ErrorType
import com.neophron88.feature.result.TypeException
import com.neophron88.upcoming.R

@StringRes
fun LoadState.getMessageErrorIfExists(): Int? {
    if (this !is LoadState.Error) return null
    val error = this.error
    if (error !is TypeException) return null

    return when (error.errorType) {
        is ErrorType.BackendFailed -> R.string.server_not_responding
        is ErrorType.NoConnection -> R.string.no_connection
        else -> R.string.something_went_wrong
    }
}
