package com.neophron88.feature.exceptions

import com.neophron88.feature.result.NetworkErrorType
import com.neophron88.feature.result.SingleResult
import com.neophron88.network.base.BackendSideException
import com.neophron88.network.base.ClientSideException
import com.neophron88.network.base.ConnectionException
import com.neophron88.network.base.UnknownNetworkException

inline fun <T> wrapNetworkExceptions(run: () -> T): SingleResult<T> =
    try {
        SingleResult.Success(run())
    } catch (e: ConnectionException) {
        SingleResult.Error(NetworkErrorType.NoConnection)
    } catch (e: BackendSideException) {
        SingleResult.Error(NetworkErrorType.BackendFailed)
    } catch (e: ClientSideException) {
        SingleResult.Error(NetworkErrorType.Unknown)
    } catch (e: UnknownNetworkException) {
        SingleResult.Error(NetworkErrorType.Unknown)
    }

