package com.example.animequotes.data.data_sources.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.animequotes.domain.entities.QuoteDatabaseModel

@Database(entities = [QuoteDatabaseModel::class], version = 2)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object{
        const val DATABASE_NAME = "quotes"
    }
}