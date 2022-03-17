package com.example.animequotes.data.repositories

import android.util.Log
import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesAPIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class QuotesAPIRepositoryImpl @Inject constructor(
    private val retrofitClient: RetrofitClient): QuotesAPIRepository {

    init {
        Log.d("Repo", "APIRepository was created")
    }

    override suspend fun getQuotes(): Flow<DataState<List<Quote>>> {
        return flow <DataState<List<Quote>>> {
            val response = retrofitClient.retrofitServices.getRandomQuotes()

            emit(DataState.success(response.body()))

        }.flowOn(Dispatchers.IO)
    }
}