package com.example.animequotes.domain.usecases.network

import com.example.animequotes.utils.Resource
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesMainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetQuotesUseCase @Inject constructor(
    private val quotesMainRepository: QuotesMainRepository) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<Resource<List<Quote>>> {
        return quotesMainRepository.getQuotes(fetchFromRemote)
    }
}