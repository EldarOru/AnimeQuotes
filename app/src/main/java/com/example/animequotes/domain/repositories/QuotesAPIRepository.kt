package com.example.animequotes.domain.repositories

import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesAPIRepository {

    suspend fun getQuotes(): Flow<DataState<List<Quote>>>
}