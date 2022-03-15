package com.example.animequotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.data.data_sources.network.Status
import com.example.animequotes.data.repositories.QuotesAPIRepositoryImpl
import com.example.animequotes.domain.entities.Quotes
import com.example.animequotes.domain.usecases.GetQuotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteListViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase): ViewModel() {

    private val _quotesState = MutableStateFlow(
        DataState(
            Status.LOADING,
            Quotes(), ""
        )
    )
    val quotesState: StateFlow<DataState<Quotes>> = _quotesState

    init {
        getQuotes()
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