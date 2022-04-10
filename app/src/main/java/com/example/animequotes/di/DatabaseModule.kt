package com.example.animequotes.di

import android.content.Context
import androidx.room.Room
import com.example.animequotes.data.data_sources.database.cached_database.CachedQuoteDatabase
import com.example.animequotes.data.data_sources.database.favourite_database.QuoteDatabase
import com.example.animequotes.data.repositories.CachedQuotesRepositoryImpl
import com.example.animequotes.data.repositories.QuotesDatabaseRepositoryImpl
import com.example.animequotes.domain.repositories.CachedQuotesRepository
import com.example.animequotes.domain.repositories.QuotesDatabaseRepository
import com.example.animequotes.domain.usecases.database.cached_database.DeleteAllQuotesUseCase
import com.example.animequotes.domain.usecases.database.cached_database.GetCachedQuotesUseCase
import com.example.animequotes.domain.usecases.database.cached_database.InsertAllQuotesUseCase
import com.example.animequotes.domain.usecases.database.cached_database.UpdateQuoteUseCase
import com.example.animequotes.domain.usecases.database.favourite_database.DeleteQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.favourite_database.GetQuoteByTextDatabaseUseCase
import com.example.animequotes.domain.usecases.database.favourite_database.GetQuotesDatabaseUseCase
import com.example.animequotes.domain.usecases.database.favourite_database.InsertQuoteDatabaseUseCase
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
    fun provideQuoteDatabase(@ApplicationContext app:Context): QuoteDatabase {
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
    fun provideGetQuotesDatabaseUseCase(rep: QuotesDatabaseRepository): GetQuotesDatabaseUseCase {
        return GetQuotesDatabaseUseCase(rep)
    }

    @Provides
    fun provideInsertQuoteDatabaseUseCase(rep: QuotesDatabaseRepository): InsertQuoteDatabaseUseCase {
        return InsertQuoteDatabaseUseCase(rep)
    }

    @Provides
    fun provideGetQuoteByTextDatabaseUseCase(rep: QuotesDatabaseRepository): GetQuoteByTextDatabaseUseCase {
        return GetQuoteByTextDatabaseUseCase(rep)
    }

    @Provides
    fun provideDeleteQuoteByTextDatabaseUseCase(rep: QuotesDatabaseRepository): DeleteQuoteByTextDatabaseUseCase {
        return DeleteQuoteByTextDatabaseUseCase(rep)
    }

    @Provides
    @Singleton
    fun provideCachedQuoteDatabase(@ApplicationContext app:Context): CachedQuoteDatabase {
        return Room.databaseBuilder(
            app,
            CachedQuoteDatabase::class.java,
            CachedQuoteDatabase.DATABASE_NAME
            //TODO CHANGE
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideCachedQuotesRepository(db: CachedQuoteDatabase): CachedQuotesRepository{
        return CachedQuotesRepositoryImpl(db.cachedQuoteDao())
    }

    @Provides
    fun provideDeleteAllQuotesUseCase(rep: CachedQuotesRepository): DeleteAllQuotesUseCase {
        return DeleteAllQuotesUseCase(rep)
    }

    @Provides
    fun provideGetCachedQuotesUseCase(rep: CachedQuotesRepository): GetCachedQuotesUseCase {
        return GetCachedQuotesUseCase(rep)
    }

    @Provides
    fun provideInsertAllQuotesUseCase(rep: CachedQuotesRepository): InsertAllQuotesUseCase {
        return InsertAllQuotesUseCase(rep)
    }

    @Provides
    fun provideUpdateQuoteUseCase(rep: CachedQuotesRepository): UpdateQuoteUseCase {
        return UpdateQuoteUseCase(rep)
    }
}