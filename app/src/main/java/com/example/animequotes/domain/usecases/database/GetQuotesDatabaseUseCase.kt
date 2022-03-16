package com.example.animequotes.domain.usecases.database

import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    operator fun invoke(): Flow<List<Quote>>{
        return quotesDatabaseRepository.getQuotes()
    }
}