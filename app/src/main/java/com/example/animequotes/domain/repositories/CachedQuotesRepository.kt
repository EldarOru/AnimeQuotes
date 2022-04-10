package com.example.animequotes.domain.repositories

import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

interface CachedQuotesRepository {

    fun getQuotes(): Flow<List<Quote>>

    suspend fun insertAllQuotes(list: List<Quote>)

    suspend fun updateQuote(quote: Quote)

    fun deleteAllCachedQuotes()
}