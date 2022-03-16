package com.example.animequotes.presentation.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.animequotes.databinding.ActivityMainBinding
import com.example.animequotes.presentation.fragments.BottomNavigationFragment
import com.example.animequotes.presentation.fragments.OnFragmentInteractionListener
import com.example.animequotes.presentation.fragments.QuoteListFragment
import dagger.hilt.android.AndroidEntryPoint


@SuppressLint("StaticFieldLeak")
private lateinit var binding: ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, QuoteListFragment())
            .replace(binding.bottomContainer.id, BottomNavigationFragment())
            .commit()
    }

    override fun onChangeFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(binding.mainContainer.id, fragment, tag)
            .commit()
    }

    override fun onAddBackStack(name: String, fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .addToBackStack(name)
            .replace(binding.mainContainer.id, fragment)
            .commit()
    }

    override fun onPopBackStack() {
        for(i in 0..supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
    }

    companion object{
        const val mainFragmentTag = "MAIN_FRAGMENT"
        const val favouriteFragmentTag = "FAVOURITE_FRAGMENT"
    }
}