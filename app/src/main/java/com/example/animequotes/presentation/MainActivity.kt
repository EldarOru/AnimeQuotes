package com.example.animequotes.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animequotes.data.QuotesRepository
import com.example.animequotes.data.RetrofitClient
import com.example.animequotes.databinding.ActivityMainBinding
import com.example.animequotes.domain.Quote
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import java.io.IOException
import kotlin.random.Random

@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var quotesListAdapter: QuotesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        QuotesRepository.getQuotesList().observe(this){
            quotesListAdapter.quotesList = it

        }
    }

    private fun setRecyclerView(){
        val recyclerView = binding.rvQuotes
        recyclerView.layoutManager = LinearLayoutManager(this)
        quotesListAdapter = QuotesListAdapter()
        recyclerView.adapter = quotesListAdapter
    }

    private fun setBottomNavigation(){
        binding.bottomNavigationView.setOn

    }
}