package com.neophron88.feature.exceptions

import android.database.sqlite.SQLiteException
import com.neophron88.feature.result.DatabaseError
import com.neophron88.feature.result.SingleResult


inline fun <T> wrapDatabaseExceptions(run: () -> T): SingleResult<T> =
    try {
        SingleResult.Success(run())
    } catch (e: SQLiteException) {
        SingleResult.Error(DatabaseError(e))
    }
