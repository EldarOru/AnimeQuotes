package com.example.animequotes.domain.usecases.database

import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuoteByTextDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    suspend operator fun invoke(text: String): Quote? {
        return quotesDatabaseRepository.getQuoteByText(text)
    }
}