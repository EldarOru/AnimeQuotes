package com.example.animequotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.Status
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.usecases.database.DeleteQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.GetQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.InsertQuoteDatabaseUseCase
import com.example.animequotes.domain.usecases.network.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesListViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val insertQuoteDatabaseUseCase: InsertQuoteDatabaseUseCase,
    private val getQuoteByTextDatabaseUseCase: GetQuoteByTextDatabaseUseCase,
    private val deleteQuoteByTextDatabaseUseCase: DeleteQuoteByTextDatabaseUseCase
): ViewModel() {

    private val _quotesState = MutableStateFlow(
        DataState(
            Status.DEFAULT,
            listOf<Quote>(), ""
        )
    )
    val quotesState: StateFlow<DataState<List<Quote>>>
    get() = _quotesState

    fun insertQuote(quote: Quote){
        //TODO CHANGE
        val quoteDatabase = QuoteDatabaseModel(
            quote.anime,
            quote.character,
            quote.quote)
        viewModelScope.launch(Dispatchers.IO) {
            if (getQuoteByTextDatabaseUseCase.invoke(quoteDatabase.quote) != null) {
                deleteQuoteByTextDatabaseUseCase.invoke(quoteDatabase.quote)
            } else {
                insertQuoteDatabaseUseCase.invoke(quoteDatabase)
            }
        }
    }

    fun getQuotes(){
        _quotesState.value = DataState.loading()

        viewModelScope.launch {
            getQuotesUseCase.invoke()
                .catch {
                    _quotesState.value = DataState.error(it.message.toString())
                }.collect {
                    _quotesState.value = DataState.success(it.data)
                }
        }
    }
}