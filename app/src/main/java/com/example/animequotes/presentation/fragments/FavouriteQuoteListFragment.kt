package com.example.animequotes.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.databinding.FavouriteQuoteListFragmentBinding
import com.example.animequotes.presentation.adapters.FavouriteQuotesListAdapter
import com.example.animequotes.presentation.adapters.QuotesListAdapter
import com.example.animequotes.presentation.viewmodels.FavouriteQuotesListViewModel
import com.example.animequotes.presentation.viewmodels.QuotesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteQuoteListFragment: Fragment() {
    private lateinit var favouriteQuotesListAdapter: FavouriteQuotesListAdapter
    private val favouriteQuotesListViewModel: FavouriteQuotesListViewModel by viewModels()
    private var binding: FavouriteQuoteListFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FavouriteQuoteListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setRecyclerView(){
        val recyclerView = binding?.rvFavouritesQuotes
        recyclerView?.layoutManager = LinearLayoutManager(context)
        favouriteQuotesListAdapter = FavouriteQuotesListAdapter()
        recyclerView?.adapter = favouriteQuotesListAdapter
    }

    fun setData(){
        lifecycleScope.launch {
            favouriteQuotesListViewModel.quotesState.collect {
                it.let {
                    favouriteQuotesListAdapter.quotesList = it
                }
            }
        }
    }
}