package com.example.animequotes.presentation.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.animequotes.R
import com.example.animequotes.databinding.BottomNavigationFragmentBinding
import com.example.animequotes.presentation.activities.MainActivity

class BottomNavigationFragment: Fragment() {
    private var binding: BottomNavigationFragmentBinding? = null
    private lateinit var onFragmentsInteractionsListener: OnFragmentInteractionListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener){
            onFragmentsInteractionsListener = context
        }else{
            throw RuntimeException("Activity must implement OnFragmentInteractionsListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  BottomNavigationFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.main_button -> {
                    onFragmentsInteractionsListener.onChangeFragment(
                        QuoteListFragment(),
                        MainActivity.mainFragmentTag
                    )
                    return@setOnItemSelectedListener true
                }
                R.id.favourite_button -> {
                    onFragmentsInteractionsListener.onChangeFragment(
                        FavouriteQuoteListFragment(),
                        MainActivity.favouriteFragmentTag
                    )
                    return@setOnItemSelectedListener true
                }
                else -> {
                    Toast.makeText(activity, "f", Toast.LENGTH_LONG).show()
                    return@setOnItemSelectedListener true
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}