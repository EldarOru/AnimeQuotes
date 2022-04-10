package com.example.animequotes.domain.usecases.database.cached_database

import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.CachedQuotesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCachedQuotesUseCase @Inject constructor(
    private val cachedQuotesRepository: CachedQuotesRepository
) {
    suspend operator fun invoke(): Flow<List<Quote>> {
        return cachedQuotesRepository.getQuotes()
    }
}