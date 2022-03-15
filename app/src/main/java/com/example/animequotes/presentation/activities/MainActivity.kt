package com.example.animequotes.presentation.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animequotes.databinding.ActivityMainBinding
import com.example.animequotes.presentation.fragments.BottomNavigationFragment
import com.example.animequotes.presentation.fragments.QuoteListFragment
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.quoteListContainer.id, QuoteListFragment())
            .replace(binding.bottomNavigationContainer.id, BottomNavigationFragment())
            .commit()


    }

}