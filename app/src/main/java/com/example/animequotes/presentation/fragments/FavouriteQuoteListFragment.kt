package com.example.animequotes.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animequotes.R
import com.example.animequotes.presentation.adapters.FavouriteQuotesListAdapter
import com.example.animequotes.presentation.viewmodels.FavouriteQuotesListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FavouriteQuoteListFragment : Fragment(R.layout.favourite_quote_list_fragment) {
    private lateinit var favouriteQuotesListAdapter: FavouriteQuotesListAdapter
    private val favouriteQuotesListViewModel: FavouriteQuotesListViewModel by viewModels()
    private var recyclerView: RecyclerView? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
        setRecyclerView()
        setData()
    }

    private fun setViews(view: View) {
        recyclerView = view.findViewById(R.id.rv_favourites_quotes)
    }

    private fun setRecyclerView() {
        val recyclerView = recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context)
        favouriteQuotesListAdapter = FavouriteQuotesListAdapter()
        recyclerView?.adapter = favouriteQuotesListAdapter
    }

    private fun setData() {
        lifecycleScope.launch {
            favouriteQuotesListViewModel.quotesState.collect {
                it.let {
                    favouriteQuotesListAdapter.quotesList = it
                }
            }
        }
    }
}