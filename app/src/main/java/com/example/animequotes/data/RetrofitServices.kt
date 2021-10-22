package com.example.animequotes.data
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animequotes.domain.Quote
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitServices {
    @GET("/api/random")
    suspend fun getRandomQuote(): Response<Quote>

    @GET("/api/quotes")
    suspend fun getTenRandomQuotesFromServer(): Response<List<Quote>>
}