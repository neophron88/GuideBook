package com.neophron88.upcoming.presentation.contract

import android.view.MenuItem
import androidx.annotation.MenuRes
import androidx.lifecycle.LifecycleOwner


interface ActionBarBehaviorHandler {
    fun setActionBarBehavior(behavior: ActionBarBehavior)
}


abstract class ActionBarBehavior(
    val lifecycleOwner: LifecycleOwner
) {
    abstract val title: String
    abstract val shouldApplyNavigateUp: Boolean
    open val applyMenu: MenuHandler? = null

}

typealias OnMenuItemClick = (MenuItem) -> Boolean
class MenuHandler(
    @MenuRes val menu: Int,
    val onMenuItemClick: OnMenuItemClick
)
