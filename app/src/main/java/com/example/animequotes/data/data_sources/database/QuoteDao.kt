package com.example.animequotes.data.data_sources.database

import androidx.room.*
import com.example.animequotes.domain.entities.QuoteDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Query("SELECT * FROM favourites_quotes")
    fun getQuotes(): Flow<List<QuoteDatabaseModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quoteDatabaseModel: QuoteDatabaseModel)

    @Delete
    suspend fun deleteQuote(quoteDatabaseModel: QuoteDatabaseModel)

    @Query("SELECT * FROM favourites_quotes WHERE quote = :quoteText")
    fun getQuoteByText(quoteText: String): QuoteDatabaseModel?

    @Query("DELETE FROM favourites_quotes WHERE quote = :quoteText")
    fun deleteQuoteByText(quoteText: String)

    @Query("DELETE FROM favourites_quotes")
    fun deleteAll()
}