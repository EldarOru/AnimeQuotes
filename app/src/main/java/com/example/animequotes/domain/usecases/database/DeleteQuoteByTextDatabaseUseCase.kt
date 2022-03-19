package com.example.animequotes.domain.usecases.database

import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import javax.inject.Inject

class DeleteQuoteByTextDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    suspend operator fun invoke(text: String) {
        return quotesDatabaseRepository.deleteQuoteByText(text)
    }
}