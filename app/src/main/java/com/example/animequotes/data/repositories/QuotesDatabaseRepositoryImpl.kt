package com.example.animequotes.data.repositories

import com.example.animequotes.data.data_sources.database.favourite_database.QuoteDao
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QuotesDatabaseRepositoryImpl @Inject constructor(
    private val quoteDao: QuoteDao
): QuotesDatabaseRepository {

    override fun getQuotes(): Flow<List<QuoteDatabaseModel>> {
        return quoteDao.getQuotes()
    }

    override suspend fun insertQuote(quoteDatabaseModel: QuoteDatabaseModel) {
        quoteDao.insertQuote(quoteDatabaseModel)
    }

    override suspend fun deleteQuote(quoteDatabaseModel: QuoteDatabaseModel) {
        quoteDao.deleteQuote(quoteDatabaseModel)
    }

    override suspend fun deleteQuoteByText(text: String) {
        quoteDao.deleteQuoteByText(text)
    }

    override suspend fun getQuoteByText(text: String): QuoteDatabaseModel? {
        return quoteDao.getQuoteByText(text)
    }
}