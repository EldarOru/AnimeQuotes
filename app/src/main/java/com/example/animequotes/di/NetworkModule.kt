package com.example.animequotes.di

import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.data.repositories.QuotesAPIRepositoryImpl
import com.example.animequotes.domain.repositories.QuotesAPIRepository
import com.example.animequotes.domain.usecases.network.GetQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideQuotesRepository(retrofitClient: RetrofitClient): QuotesAPIRepository{
        return QuotesAPIRepositoryImpl(retrofitClient)
    }

    @Provides
    fun provideGetQuotesUseCase(repository: QuotesAPIRepository): GetQuotesUseCase {
        return GetQuotesUseCase(repository)
    }

    @Provides
    fun provideRetrofitClient(): RetrofitClient{
        return RetrofitClient()
    }
}