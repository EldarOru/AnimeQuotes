package com.example.animequotes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.animequotes.databinding.FavouriteQuoteListFragmentBinding

class FavouriteQuoteListFragment: Fragment() {
    private var binding: FavouriteQuoteListFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouriteQuoteListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }
}