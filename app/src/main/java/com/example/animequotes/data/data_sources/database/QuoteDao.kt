package com.example.animequotes.data.data_sources.database

import androidx.room.*
import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow
import retrofit2.http.DELETE

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote")
    fun getQuotes(): Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)
}