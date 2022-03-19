package com.example.animequotes.domain.usecases.database

import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import javax.inject.Inject

class GetQuoteByTextDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    suspend operator fun invoke(text: String): QuoteDatabaseModel? {
        return quotesDatabaseRepository.getQuoteByText(text)
    }
}