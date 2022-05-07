package com.example.animequotes.domain.repositories

import com.example.animequotes.utils.Resource
import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesMainRepository {

    //TODO CHANGE
    suspend fun getQuotes(fetchFromRemote: Boolean): Flow<Resource<List<Quote>>>
}