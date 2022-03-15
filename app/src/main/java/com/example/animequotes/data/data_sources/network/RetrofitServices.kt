package com.example.animequotes.data.data_sources.network
import com.example.animequotes.domain.entities.Quote
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServices {
    @GET("/api/random")
    suspend fun getRandomQuote(): Response<Quote>

    @GET("/api/quotes")
    suspend fun getRandomQuotes(): Response<List<Quote>>
}