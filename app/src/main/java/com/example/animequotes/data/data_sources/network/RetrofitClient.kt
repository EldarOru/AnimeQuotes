package com.example.animequotes.data.data_sources.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    val retrofitServices : RetrofitServices by lazy {
        Retrofit.Builder()
            .baseUrl("https://animechan.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitServices::class.java)
    }
}