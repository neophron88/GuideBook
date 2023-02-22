package com.neophron88.feature.result

sealed class ErrorType

sealed class NetworkErrorType : ErrorType() {
    object NoConnection : NetworkErrorType()
    object BackendFailed : NetworkErrorType()
    object Unknown : NetworkErrorType()
}

object DatabaseError : ErrorType()

