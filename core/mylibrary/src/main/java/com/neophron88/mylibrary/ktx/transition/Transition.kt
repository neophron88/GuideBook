package com.neophron88.mylibrary.ktx.transition

import androidx.transition.Transition


inline fun Transition.onEnd(crossinline onEnd: Transition.() -> Unit):Transition {
    return addListener(object : Transition.TransitionListener {
        override fun onTransitionStart(transition: Transition) = Unit

        override fun onTransitionEnd(transition: Transition) = onEnd(transition)

        override fun onTransitionCancel(transition: Transition) = Unit

        override fun onTransitionPause(transition: Transition) = Unit

        override fun onTransitionResume(transition: Transition) = Unit
    }
    )
}