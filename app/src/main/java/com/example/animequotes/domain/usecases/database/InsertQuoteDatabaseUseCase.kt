package com.example.animequotes.domain.usecases.database

import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import javax.inject.Inject

class InsertQuoteDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    suspend operator fun invoke(quote: Quote){
        quotesDatabaseRepository.insertQuote(quote)
    }
}