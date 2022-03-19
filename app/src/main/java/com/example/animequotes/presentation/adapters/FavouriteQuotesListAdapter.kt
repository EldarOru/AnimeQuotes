package com.example.animequotes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.animequotes.databinding.FavouriteQuoteItemBinding
import com.example.animequotes.domain.entities.Quote
import com.example.animequotes.domain.entities.QuoteDatabaseModel

class FavouriteQuotesListAdapter(): RecyclerView.Adapter<FavouriteQuotesListAdapter.FavouriteQuotesListHolder>() {

    var quotesList = listOf<QuoteDatabaseModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteQuotesListHolder {
        return FavouriteQuotesListHolder(
            FavouriteQuoteItemBinding.inflate(LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: FavouriteQuotesListHolder, position: Int) {
        val quote = quotesList[position]
        holder.apply {
            favouriteQuoteView.animeName.text = quote.anime
            favouriteQuoteView.characterName.text = quote.character
            favouriteQuoteView.quote.text = quote.quote
        }
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    class FavouriteQuotesListHolder(val favouriteQuoteView: FavouriteQuoteItemBinding):
        RecyclerView.ViewHolder(favouriteQuoteView.root)
}