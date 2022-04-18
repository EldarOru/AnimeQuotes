package com.example.animequotes.domain.repositories

import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesMainRepository {

    //TODO CHANGE
    suspend fun getQuotes(fetchFromRemote: Boolean): Flow<DataState<List<Quote>>>
}