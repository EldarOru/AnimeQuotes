package com.example.animequotes.domain.repositories

import com.example.animequotes.domain.entities.QuoteDatabaseModel
import kotlinx.coroutines.flow.Flow

interface QuotesDatabaseRepository {

    fun getQuotes(): Flow<List<QuoteDatabaseModel>>

    suspend fun insertQuote(quoteDatabaseModel: QuoteDatabaseModel)

    suspend fun deleteQuote(quoteDatabaseModel: QuoteDatabaseModel)

    suspend fun deleteQuoteByText(text: String)

    suspend fun getQuoteByText(text: String): QuoteDatabaseModel?
}