package com.example.animequotes.data.data_sources.database.favourite_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animequotes.data.data_sources.database.favourite_database.QuoteDao
import com.example.animequotes.domain.entities.QuoteDatabaseModel

@Database(entities = [QuoteDatabaseModel::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object{
        const val DATABASE_NAME = "quotes"
    }
}