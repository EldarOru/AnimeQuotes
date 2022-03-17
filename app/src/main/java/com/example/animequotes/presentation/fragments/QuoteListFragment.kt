package com.example.animequotes.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.data.data_sources.network.Status
import com.example.animequotes.databinding.QuoteListFragmentBinding
import com.example.animequotes.presentation.adapters.QuotesListAdapter
import com.example.animequotes.presentation.viewmodels.QuoteListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuoteListFragment: Fragment() {
    private lateinit var quotesListAdapter: QuotesListAdapter
    private val quoteListViewModel: QuoteListViewModel by viewModels()
    private var binding: QuoteListFragmentBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = QuoteListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setState()
        setListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun setRecyclerView(){
        val recyclerView = binding?.rvQuotes
        recyclerView?.layoutManager = LinearLayoutManager(context)
        quotesListAdapter = QuotesListAdapter()
        recyclerView?.adapter = quotesListAdapter
    }

    private fun setListeners(){
        binding?.reloadButton?.setOnClickListener {
            quoteListViewModel.getQuotes()
        }
        quotesListAdapter.onClickListener = {
            it.isFavourite = !it.isFavourite
            quoteListViewModel.insertQuote(it)
            quotesListAdapter.quotesList = quoteListViewModel.quotesState.value.data!!
        }
    }

    private fun setState(){
        lifecycleScope.launch {
            quoteListViewModel.quotesState.collect{
                when(it.status){
                    Status.LOADING -> {
                        binding?.loadingPb?.visibility = View.VISIBLE
                    }
                    Status.SUCCESS -> {
                        binding?.loadingPb?.visibility = View.GONE
                        it.data?.let { list ->
                            quotesListAdapter.quotesList = list
                        }
                    }
                    else -> {
                        binding?.loadingPb?.visibility = View.GONE
                        Toast.makeText(requireContext(), it.msg.toString(), Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}