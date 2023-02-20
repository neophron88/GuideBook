@file:Suppress("unused")

package com.neophron88.mylibrary.single_use_data


typealias Observer<T> = (T) -> Unit

fun <T : Any> MutableSingleUseData<T>.toSingleUseData(): SingleUseData<T> = this

