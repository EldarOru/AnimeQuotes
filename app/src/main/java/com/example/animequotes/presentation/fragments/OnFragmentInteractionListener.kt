package com.example.animequotes.presentation.fragments

import androidx.fragment.app.Fragment

interface OnFragmentInteractionListener {

    fun onChangeFragment(fragment: Fragment, tag: String)

    fun onAddBackStack(name: String, fragment: Fragment)

    fun onPopBackStack()

}