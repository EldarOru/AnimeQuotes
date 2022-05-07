package com.example.animequotes.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.utils.Resource
import com.example.animequotes.utils.Status
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.usecases.database.favourite_database.InsertQuoteDatabaseUseCase
import com.example.animequotes.domain.usecases.network.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesListViewModel @Inject constructor(
    //TODO CHANGE USE CASES
    private val getQuotesUseCase: GetQuotesUseCase,
    private val insertQuoteDatabaseUseCase: InsertQuoteDatabaseUseCase
) : ViewModel() {

    init {
        getQuotes()
        Log.d("Hi", "I WAS CREATED")
    }

    private val _quotesState = MutableStateFlow(
        Resource(
            Status.DEFAULT,
            listOf<Quote>(), ""
        )
    )

    val quotesState: StateFlow<Resource<List<Quote>>>
        get() = _quotesState

    fun insertQuote(quote: Quote) {
        //TODO CHANGE
        val quoteDatabase = QuoteDatabaseModel(
            quote.anime,
            quote.character,
            quote.quote
        )
        viewModelScope.launch(Dispatchers.IO) {
            insertQuoteDatabaseUseCase.invoke(quoteDatabase)
        }
    }

    fun getQuotes(fetchFromRemote: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            getQuotesUseCase.invoke(fetchFromRemote)
                .collect {
                    _quotesState.value = it
                }
        }
    }
}