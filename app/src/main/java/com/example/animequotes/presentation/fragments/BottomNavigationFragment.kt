package com.example.animequotes.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.animequotes.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationFragment : Fragment(R.layout.bottom_navigation_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bottomNavigation = view.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navController =
            (activity?.supportFragmentManager?.findFragmentById(R.id.main_container) as NavHostFragment).navController
        NavigationUI.setupWithNavController(bottomNavigation, navController)
    }
}