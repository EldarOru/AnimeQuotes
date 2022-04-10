package com.example.animequotes.domain.usecases.database.cached_database

import com.example.animequotes.domain.repositories.CachedQuotesRepository
import javax.inject.Inject

class DeleteAllQuotesUseCase @Inject constructor(
    private val cachedQuotesRepository: CachedQuotesRepository
) {
    suspend operator fun invoke() {
        cachedQuotesRepository.deleteAllCachedQuotes()
    }
}