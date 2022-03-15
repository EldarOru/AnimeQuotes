package com.example.animequotes.domain.usecases

import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.entities.Quotes
import com.example.animequotes.domain.repositories.QuotesAPIRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val quotesAPIRepository: QuotesAPIRepository) {

    suspend operator fun invoke(): Flow<DataState<Quotes>> {
        return quotesAPIRepository.getQuotes()
    }

}