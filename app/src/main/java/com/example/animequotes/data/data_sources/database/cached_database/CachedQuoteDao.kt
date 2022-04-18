package com.example.animequotes.data.data_sources.database.cached_database

import androidx.room.*
import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface CachedQuoteDao {

    @Query("SELECT * FROM cached_quotes")
    fun getCachedQuotes(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuotes(list: List<Quote>)

    @Update
    suspend fun updateQuote(quote: Quote)

    @Query("DELETE FROM cached_quotes")
    fun deleteAllCachedQuotes()
}

