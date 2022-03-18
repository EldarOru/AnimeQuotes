package com.example.animequotes.data.data_sources.database

import androidx.room.*
import com.example.animequotes.domain.entities.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes")
    fun getQuotes(): Flow<List<Quote>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Delete
    suspend fun deleteQuote(quote: Quote)

    @Query("SELECT * FROM quotes WHERE quote = :quoteText")
    fun getQuoteByText(quoteText: String): Quote?

    @Query("DELETE FROM quotes WHERE quote = :quoteText")
    fun deleteQuoteByText(quoteText: String)

    @Query("DELETE FROM quotes")
    fun deleteAll()
}