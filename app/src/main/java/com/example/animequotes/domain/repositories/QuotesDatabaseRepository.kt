package com.example.animequotes.domain.repositories

import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesDatabaseRepository {

    fun getQuotes(): Flow<List<Quote>>

    suspend fun insertQuote(quote: Quote)

    suspend fun deleteQuote(quote: Quote)

    suspend fun deleteQuoteByText(text: String)

    suspend fun getQuoteByText(text: String): Quote?
}