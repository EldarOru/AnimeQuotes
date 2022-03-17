package com.example.animequotes.data.repositories

import com.example.animequotes.data.data_sources.database.QuoteDao
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesDatabaseRepositoryImpl @Inject constructor(
    private val quoteDao: QuoteDao): QuotesDatabaseRepository {

    override fun getQuotes(): Flow<List<Quote>> {
        return quoteDao.getQuotes()
    }

    override suspend fun insertQuote(quote: Quote) {
        quoteDao.insertQuote(quote)
    }

    override suspend fun deleteQuote(quote: Quote) {
        quoteDao.deleteQuote(quote)
    }

    override suspend fun deleteQuoteByText(text: String) {
        quoteDao.deleteQuoteByText(text)
    }

    override suspend fun getQuoteByText(text: String): Quote? {
        return quoteDao.getQuoteByText(text)
    }
}