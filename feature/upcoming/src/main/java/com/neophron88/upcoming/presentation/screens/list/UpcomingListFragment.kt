package com.neophron88.upcoming.presentation.screens.list

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.neophron88.mylibrary.ktx.fragment.viewLifeCycle
import com.neophron88.mylibrary.viewbinding_delegate.viewBindings
import com.neophron88.upcoming.R
import com.neophron88.upcoming.databinding.FragmentUpcomingListBinding

class UpcomingListFragment : Fragment(R.layout.fragment_upcoming_list) {

    private val binding: FragmentUpcomingListBinding by viewBindings()
    private val navController: NavController by viewLifeCycle { findNavController() }

}