package com.example.animequotes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.animequotes.R
import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.data.repositories.QuotesAPIRepositoryImpl
import com.example.animequotes.databinding.BottomNavigationFragmentBinding

class BottomNavigationFragment: Fragment() {
    private var binding: BottomNavigationFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  BottomNavigationFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val quotesAPIRepositoryImpl = QuotesAPIRepositoryImpl(RetrofitClient())
        /*
        binding?.bottomNavigation?.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.search_button -> quotesAPIRepositoryImpl.getQuotes()
            }
            true
        }

         */
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}