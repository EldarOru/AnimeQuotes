package com.example.animequotes.data.data_sources.database.cached_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animequotes.domain.entities.Quote


@Database(entities = [Quote::class], version = 1)
abstract class CachedQuoteDatabase : RoomDatabase() {
    abstract fun cachedQuoteDao(): CachedQuoteDao

    companion object {
        const val DATABASE_NAME = "cached_quotes"
    }
}

