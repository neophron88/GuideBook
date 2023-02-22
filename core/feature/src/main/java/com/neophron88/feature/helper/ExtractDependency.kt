package com.neophron88.feature.helper

import androidx.fragment.app.Fragment
import com.neophron88.feature.contract.DependencyProvider

inline fun <reified T> Fragment.extractDependency(): T {
    val application = requireActivity().application
    if (application !is DependencyProvider)
        error("The Application needs to implement DependencyProvider")

    val dependency = application.dependency
    if (dependency !is T)
        error("The top level dagger component did not implement ${T::class.simpleName}")

    return dependency
}