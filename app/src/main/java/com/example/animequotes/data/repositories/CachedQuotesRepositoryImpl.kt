package com.example.animequotes.data.repositories

import com.example.animequotes.data.data_sources.database.cached_database.CachedQuoteDao
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.CachedQuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CachedQuotesRepositoryImpl @Inject constructor(
    private val cachedQuoteDao: CachedQuoteDao
): CachedQuotesRepository {

    override fun getQuotes(): Flow<List<Quote>> {
        return cachedQuoteDao.getCachedQuotes()
    }

    override suspend fun insertAllQuotes(list: List<Quote>) {
        cachedQuoteDao.insertAllQuotes(list)
    }

    override suspend fun updateQuote(quote: Quote) {
        cachedQuoteDao.updateQuote(quote)
    }

    override fun deleteAllCachedQuotes() {
        cachedQuoteDao.deleteAllCachedQuotes()
    }
}