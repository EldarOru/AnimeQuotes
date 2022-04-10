package com.example.animequotes.domain.usecases.database.cached_database

import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.CachedQuotesRepository
import javax.inject.Inject

class UpdateQuoteUseCase @Inject constructor(
    private val cachedQuotesRepository: CachedQuotesRepository
) {
    suspend operator fun invoke(quote: Quote) {
        cachedQuotesRepository.updateQuote(quote)
    }
}