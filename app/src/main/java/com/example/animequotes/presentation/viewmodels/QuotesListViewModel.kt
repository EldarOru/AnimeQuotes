package com.example.animequotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.Status
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.usecases.database.cached_database.DeleteAllQuotesUseCase
import com.example.animequotes.domain.usecases.database.cached_database.GetCachedQuotesUseCase
import com.example.animequotes.domain.usecases.database.cached_database.InsertAllQuotesUseCase
import com.example.animequotes.domain.usecases.database.cached_database.UpdateQuoteUseCase
import com.example.animequotes.domain.usecases.database.favourite_database.DeleteQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.favourite_database.InsertQuoteDatabaseUseCase
import com.example.animequotes.domain.usecases.network.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesListViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val insertQuoteDatabaseUseCase: InsertQuoteDatabaseUseCase,
    private val deleteQuoteByTextDatabaseUseCase: DeleteQuoteByTextDatabaseUseCase,
    private val getCachedQuotesUseCase: GetCachedQuotesUseCase,
    private val deleteAllQuotesUseCase: DeleteAllQuotesUseCase,
    private val insetAllQuotesUseCase: InsertAllQuotesUseCase,
    private val updateQuoteUseCase: UpdateQuoteUseCase
) : ViewModel() {

    init {
        getCachedQuotes()
    }

    private val _quotesState = MutableStateFlow(
        DataState(
            Status.DEFAULT,
            listOf<Quote>(), ""
        )
    )

    val quotesState: StateFlow<DataState<List<Quote>>>
        get() = _quotesState

    fun insertQuote(quote: Quote) {
        //TODO CHANGE
        val quoteDatabase = QuoteDatabaseModel(
            quote.anime,
            quote.character,
            quote.quote
        )
        viewModelScope.launch(Dispatchers.IO) {
            if (quote.isFavourite) {
                deleteQuoteByTextDatabaseUseCase.invoke(quoteDatabase.quote)
            } else {
                insertQuoteDatabaseUseCase.invoke(quoteDatabase)
            }
            updateQuoteUseCase.invoke(
                Quote(
                    quote.anime,
                    quote.character,
                    quote.quote,
                    !quote.isFavourite,
                    id = quote.id
                )
            )
        }
    }

    fun getQuotes() {
        _quotesState.value = DataState.loading()

        viewModelScope.launch(Dispatchers.IO) {
            getQuotesUseCase.invoke()
                .catch {
                    _quotesState.value = DataState.error(it.message.toString())
                }.collect {
                    deleteAllQuotesUseCase.invoke()
                    _quotesState.value = DataState.success(it.data)
                    it.data?.let { it1 -> insetAllQuotesUseCase.invoke(it1) }
                }
        }
    }

    private fun getCachedQuotes() {
        viewModelScope.launch(Dispatchers.IO) {
            getCachedQuotesUseCase.invoke()
                .collect {
                    _quotesState.value = DataState.success(it)
                }
        }
    }
}