package com.example.animequotes.domain.usecases.database.favourite_database

import com.example.animequotes.domain.entities.QuoteDatabaseModel
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import javax.inject.Inject

class InsertQuoteDatabaseUseCase @Inject constructor(
    private val quotesDatabaseRepository: QuotesDatabaseRepository) {
    suspend operator fun invoke(quoteDatabaseModel: QuoteDatabaseModel){
        quotesDatabaseRepository.insertQuote(quoteDatabaseModel)
    }
}