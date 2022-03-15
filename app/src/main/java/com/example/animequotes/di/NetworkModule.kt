package com.example.animequotes.di

import com.example.animequotes.data.data_sources.network.RetrofitClient
import com.example.animequotes.data.repositories.QuotesAPIRepositoryImpl
import com.example.animequotes.domain.repositories.QuotesAPIRepository
import com.example.animequotes.domain.usecases.GetQuotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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
    @Singleton
    fun provideGetQuotesUseCase(repository: QuotesAPIRepository): GetQuotesUseCase{
        return GetQuotesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(): RetrofitClient{
        return RetrofitClient()
    }
}