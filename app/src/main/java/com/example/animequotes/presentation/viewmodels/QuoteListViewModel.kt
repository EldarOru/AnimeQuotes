package com.example.animequotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.Status
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.usecases.database.DeleteQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.GetQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.InsertQuoteDatabaseUseCase
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
class QuoteListViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val insertQuoteDatabaseUseCase: InsertQuoteDatabaseUseCase,
    private val getQuoteByTextDatabaseUseCase: GetQuoteByTextDatabaseUseCase,
    private val deleteQuoteByTextDatabaseUseCase: DeleteQuoteByTextDatabaseUseCase
): ViewModel() {

    private val _quotesState = MutableStateFlow(
        DataState(
            Status.LOADING,
            listOf<Quote>(), ""
        )
    )
    val quotesState: StateFlow<DataState<List<Quote>>>
    get() = _quotesState

    init {
        getQuotes()
    }

    fun insertQuote(quote: Quote){
        //TODO CHANGE
        viewModelScope.launch(Dispatchers.IO) {
            if (getQuoteByTextDatabaseUseCase.invoke(quote.quote) != null){
                deleteQuoteByTextDatabaseUseCase.invoke(quote.quote)
            }else{
                insertQuoteDatabaseUseCase.invoke(quote)
            }
        }
    }

    fun getQuotes(){
        _quotesState.value = DataState.loading()

        viewModelScope.launch {
            getQuotesUseCase.invoke()
                .catch {
                    _quotesState.value = DataState.error(it.message.toString())
                }
                .collect {
                    _quotesState.value = DataState.success(it.data)
                }
        }
    }
}