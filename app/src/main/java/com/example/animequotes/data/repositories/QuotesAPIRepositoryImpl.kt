package com.example.animequotes.data.repositories

import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.domain.entities.Quotes
import com.example.animequotes.domain.repositories.QuotesAPIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuotesAPIRepositoryImpl @Inject constructor(
    private val retrofitClient: RetrofitClient): QuotesAPIRepository {

    override suspend fun getQuotes(): Flow<DataState<Quotes>> {
        return flow <DataState<Quotes>> {
            val response = retrofitClient.retrofitServices.getRandomQuotes()

            emit(DataState.success(response.body()?.let { Quotes(it) }))

        }.flowOn(Dispatchers.IO)
    }

}