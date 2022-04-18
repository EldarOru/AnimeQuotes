package com.example.animequotes.data.repositories

import com.example.animequotes.data.data_sources.database.cached_database.CachedQuoteDao
import com.example.animequotes.data.data_sources.network.DataState
import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.repositories.QuotesMainRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


class QuotesMainRepositoryImpl @Inject constructor(
    private val retrofitClient: RetrofitClient,
    private val cachedQuoteDao: CachedQuoteDao
): QuotesMainRepository {

    override suspend fun getQuotes(fetchFromRemote: Boolean): Flow<DataState<List<Quote>>> {
        return flow <DataState<List<Quote>>> {
            val localData = cachedQuoteDao.getCachedQuotes()
            emit(DataState.loading())
            val shouldJustLoadFromCache = !localData.isNullOrEmpty() && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(DataState.success(localData))
                return@flow
            }

            val response = try {
                retrofitClient.retrofitServices.getRandomQuotes()
            }catch(e: IOException) {
                e.printStackTrace()
                emit(DataState.error(e.message.toString()))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(DataState.error(e.message.toString()))
                null
            }

            response?.body()?.let {
                cachedQuoteDao.deleteAllCachedQuotes()
                cachedQuoteDao.insertAllQuotes(it)
                emit(DataState.success(it))
                return@flow
            }
            //emit(DataState.default())

        }.flowOn(Dispatchers.IO)
    }
}