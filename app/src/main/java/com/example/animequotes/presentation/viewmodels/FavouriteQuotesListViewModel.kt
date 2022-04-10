package com.example.animequotes.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.usecases.database.favourite_database.GetQuotesDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteQuotesListViewModel @Inject constructor(
    private val getQuotesDatabaseUseCase: GetQuotesDatabaseUseCase
): ViewModel() {

    init {
        getFavouriteQuotes()
    }

    private val _quotesState = MutableStateFlow(listOf<QuoteDatabaseModel>())
    val quotesState: StateFlow<List<QuoteDatabaseModel>>
        get() = _quotesState

    fun getFavouriteQuotes(){
        viewModelScope.launch(Dispatchers.IO) {
            getQuotesDatabaseUseCase.invoke()
                .collect {
                    _quotesState.value = it
                }
        }
    }
}