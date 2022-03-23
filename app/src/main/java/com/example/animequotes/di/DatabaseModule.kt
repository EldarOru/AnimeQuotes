package com.example.animequotes.di

import android.content.Context
import androidx.room.Room
import com.example.animequotes.data.data_sources.database.QuoteDatabase
import com.example.animequotes.data.repositories.QuotesDatabaseRepositoryImpl
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import com.example.animequotes.domain.usecases.database.DeleteQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.GetQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.GetQuotesDatabaseUseCase
import com.example.animequotes.domain.usecases.database.InsertQuoteDatabaseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(@ApplicationContext app:Context): QuoteDatabase{
        return Room.databaseBuilder(
            app,
            QuoteDatabase::class.java,
            QuoteDatabase.DATABASE_NAME
        //TODO CHANGE
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideQuotesDatabaseRepository(db: QuoteDatabase): QuotesDatabaseRepository{
        return QuotesDatabaseRepositoryImpl(db.quoteDao())
    }

    @Provides
    fun provideGetQuotesDatabaseUseCase(rep: QuotesDatabaseRepository): GetQuotesDatabaseUseCase{
        return GetQuotesDatabaseUseCase(rep)
    }

    @Provides
    fun provideInsertQuoteDatabaseUseCase(rep: QuotesDatabaseRepository): InsertQuoteDatabaseUseCase{
        return InsertQuoteDatabaseUseCase(rep)
    }

    @Provides
    fun provideGetQuoteByTextDatabaseUseCase(rep: QuotesDatabaseRepository): GetQuoteByTextDatabaseUseCase{
        return GetQuoteByTextDatabaseUseCase(rep)
    }

    @Provides
    fun provideDeleteQuoteByTextDatabaseUseCase(rep: QuotesDatabaseRepository): DeleteQuoteByTextDatabaseUseCase{
        return DeleteQuoteByTextDatabaseUseCase(rep)
    }
}