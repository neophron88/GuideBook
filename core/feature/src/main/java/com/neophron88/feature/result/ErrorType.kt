package com.neophron88.feature.result

import android.database.SQLException

sealed class ErrorType

sealed class NetworkErrorType : ErrorType() {
    object NoConnection : NetworkErrorType()
    object BackendFailed : NetworkErrorType()
    object Unknown : NetworkErrorType()
}

class DatabaseError(e:SQLException) : ErrorType()

object EmptyDataError : ErrorType()

class TypeException(errorType: ErrorType) : Throwable()

