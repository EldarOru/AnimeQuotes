package com.example.animequotes.di

import android.content.Context
import androidx.room.Room
import com.example.animequotes.data.data_sources.database.cached_database.CachedQuoteDatabase
import com.example.animequotes.data.data_sources.database.favourite_database.QuoteDatabase
import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.data.repositories.QuotesMainRepositoryImpl
import com.example.animequotes.domain.repositories.QuotesMainRepository
import com.example.animequotes.domain.usecases.network.GetQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideQuotesRepository(retrofitClient: RetrofitClient, db: CachedQuoteDatabase): QuotesMainRepository{
        return QuotesMainRepositoryImpl(retrofitClient, db.cachedQuoteDao())
    }

    @Provides
    fun provideGetQuotesUseCase(repository: QuotesMainRepository): GetQuotesUseCase {
        return GetQuotesUseCase(repository)
    }

    @Provides
    fun provideRetrofitClient(): RetrofitClient{
        return RetrofitClient()
    }

    @Provides
    @Singleton
    fun provideCachedQuoteDatabase(@ApplicationContext app: Context): CachedQuoteDatabase {
        return Room.databaseBuilder(
            app,
            CachedQuoteDatabase::class.java,
            CachedQuoteDatabase.DATABASE_NAME
            //TODO CHANGE
        ).fallbackToDestructiveMigration().build()
    }
}