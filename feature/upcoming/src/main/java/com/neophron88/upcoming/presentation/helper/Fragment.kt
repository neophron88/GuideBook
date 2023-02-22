package com.neophron88.upcoming.presentation.helper

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.neophron88.upcoming.R


fun Fragment.showToast(@StringRes message: Int) {
    Snackbar
        .make(requireView(), message, Snackbar.LENGTH_LONG)
        .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE)
        .show()
}

fun FragmentManager.findNavController() =
    (this.findFragmentById(R.id.container) as NavHostFragment).navController
