package com.example.animequotes.presentation.fragments

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animequotes.R
import com.example.animequotes.data.data_sources.network.Status
import com.example.animequotes.presentation.adapters.QuotesListAdapter
import com.example.animequotes.presentation.viewmodels.QuotesListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteListFragment: Fragment(R.layout.quote_list_fragment) {
    private lateinit var quotesListAdapter: QuotesListAdapter
    private val quotesListViewModel: QuotesListViewModel by viewModels()
    private var floatingActionButton: FloatingActionButton? = null
    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
        setRecyclerView()
        setState()
        setListeners()
        //TODO DELETE
        quotesListViewModel.getQuotes()
    }

    private fun setViews(view: View){
        floatingActionButton = view.findViewById(R.id.reload_button)
        recyclerView = view.findViewById(R.id.rv_quotes)
        progressBar = view.findViewById(R.id.loading_pb)
    }

    private fun setRecyclerView(){
        val recyclerView = recyclerView
        recyclerView?.layoutManager = LinearLayoutManager(context)
        quotesListAdapter = QuotesListAdapter(requireActivity().applicationContext)
        recyclerView?.adapter = quotesListAdapter
    }

    private fun setListeners(){
        floatingActionButton?.setOnClickListener {
            quotesListViewModel.getQuotes(fetchFromRemote = true)
            recyclerView?.scrollToPosition(0)
        }
        quotesListAdapter.onClickListener = {
            quotesListViewModel.insertQuote(it)
        }
    }

    private fun setState(){
        lifecycleScope.launch {
            quotesListViewModel.quotesState.collect{
                when(it.status){
                    Status.LOADING -> {
                        progressBar?.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        progressBar?.visibility = View.GONE
                        it.data?.let { list ->
                            quotesListAdapter.quotesList = list
                        }
                    }
                    Status.DEFAULT -> {
                        Toast.makeText(requireContext(), "kek", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        progressBar?.visibility = View.GONE
                        Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}