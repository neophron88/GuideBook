@file:Suppress("UNCHECKED_CAST", "unused")

package com.neophron88.mylibrary.viewbinding_delegate

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A delegate that provides your viewbinding within a Fragment by lazy.
 *
 * The Delegate considers the fragment's view lifecycle.
 *
 * When using this delegate make sure the Fragment is inherited from
 * Fragment(@LayoutRes int contentLayoutId) with providing your layout in constructor or
 * create as usual in onCreateView method in order to viewBinding's delegate will be able
 * to bind to the view on its own.
 *
 * Note that accessing viewBinding while fragment's view is
 * destroyed or not created will throw IllegalStateException.
 **/

inline fun <reified VB : ViewBinding> Fragment.viewBindings(): LazyFragmentsViewBinding<VB> {
    return LazyFragmentsViewBinding(VB::class.java)
}

class LazyFragmentsViewBinding<VB : ViewBinding>(
    private val VBClazz: Class<VB>,
) : ReadOnlyProperty<Fragment, VB> {


    private var binding: VB? = null


    private val error = IllegalStateException(
        "Don't access viewBinding before onViewCreated() or after onDestroyView() inclusive"
    )

    private val observer = object : DefaultLifecycleObserver {
        override fun onDestroy(owner: LifecycleOwner) {
            binding = null
        }
    }


    override operator fun getValue(
        thisRef: Fragment,
        property: KProperty<*>
    ): VB = binding ?: tryToBind(thisRef)

    private fun tryToBind(fragment: Fragment): VB {

        val view = fragment.view ?: throw error
        val lifecycle = fragment.viewLifecycleOwner.lifecycle
        if (lifecycle.currentState == Lifecycle.State.DESTROYED) throw error

        lifecycle.addObserver(observer)

        val bindMethod = VBClazz.getMethod("bind", View::class.java)
        val newBinding = bindMethod.invoke(null, view) as VB
        binding = newBinding
        return newBinding
    }

}