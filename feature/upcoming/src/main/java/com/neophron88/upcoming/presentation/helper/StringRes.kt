package com.neophron88.upcoming.presentation.helper

import android.content.Context
import androidx.annotation.StringRes

fun Context.getStringOrNull(@StringRes resId: Int?): String? =
    resId?.let { getString(it) }
