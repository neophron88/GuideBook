package com.neophron88.feature.result


sealed class SingleResult<T> {
    class Success<T>(val value: T) : SingleResult<T>()
    class Error<T>(val type: ErrorType) : SingleResult<T>()
}


