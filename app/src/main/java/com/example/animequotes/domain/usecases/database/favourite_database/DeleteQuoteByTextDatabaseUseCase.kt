package com.example.animequotes.domain.usecases.database.favourite_database

import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DeleteQuoteByTextDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    suspend operator fun invoke(text: String) {
        return quotesDatabaseRepository.deleteQuoteByText(text)
    }
}