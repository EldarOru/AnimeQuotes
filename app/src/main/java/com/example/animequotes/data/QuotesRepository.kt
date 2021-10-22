package com.example.animequotes.data

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.animequotes.domain.Quote
import com.google.gson.JsonObject
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import retrofit2.Response
import java.io.IOException

object QuotesRepository {
    private val quotesListLiveData = MutableLiveData<List<Quote>>()
    private val quoteLiveData = MutableLiveData<Quote>()
    private var quote: Quote = Quote("DEF","DEF","DEF")
    private var quotesList = listOf<Quote>()

    fun getQuoteFromServer() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.retrofitServices.getRandomQuote()
            if (response.isSuccessful && response.body() != null) {
                quote = response.body() as Quote
                Log.e("KEK", quote.toString())

                withContext(Main){
                    updateQuoteLiveData()
                }
            }
        }
    }

    fun getTenQuotesFromServer() {
        GlobalScope.launch(Dispatchers.IO) {
            val response: Response<List<Quote>> = try {
                RetrofitClient.retrofitServices.getTenRandomQuotesFromServer()
            } catch (ex: IOException){
                Log.e("ERROR", ex.localizedMessage)
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                quotesList = response.body() as List<Quote>
                Log.e("KEK", quotesList.toString())
                Log.e("KEK", Thread.currentThread().name)
                withContext(Main){
                    updateQuoteListLiveData()
                    Log.e("KEK", Thread.currentThread().name)
                }
            }
        }

    }


    fun getQuotesList(): LiveData<List<Quote>>{
        return quotesListLiveData
    }

    private fun updateQuoteListLiveData(){
        quotesListLiveData.value = quotesList
    }

    fun getQuote(): LiveData<Quote>{
        return quoteLiveData
    }

    private fun updateQuoteLiveData(){
        quoteLiveData.value = quote
    }
}