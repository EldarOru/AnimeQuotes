package com.example.animequotes.data.data_sources.database

import androidx.room.*
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quotes")
    fun getQuotes(): Flow<List<QuoteDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quoteDatabaseModel: QuoteDatabaseModel)

    @Delete
    suspend fun deleteQuote(quoteDatabaseModel: QuoteDatabaseModel)

    @Query("SELECT * FROM quotes WHERE quote = :quoteText")
    fun getQuoteByText(quoteText: String): QuoteDatabaseModel?

    @Query("DELETE FROM quotes WHERE quote = :quoteText")
    fun deleteQuoteByText(quoteText: String)

    @Query("DELETE FROM quotes")
    fun deleteAll()
}