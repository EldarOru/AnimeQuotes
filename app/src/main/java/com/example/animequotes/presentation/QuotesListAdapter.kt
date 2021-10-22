package com.example.animequotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.animequotes.databinding.QuoteItemBinding
import com.example.animequotes.domain.Quote

class QuotesListAdapter: RecyclerView.Adapter<QuotesListAdapter.QuotesListHolder>() {
    var quotesList = listOf<Quote>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesListHolder {
        val quoteView = QuoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesListHolder(quoteView)
    }

    override fun onBindViewHolder(holder: QuotesListHolder, position: Int) {
        val quote = quotesList[position]
        holder.quoteView.animeName.text = quote.anime
        holder.quoteView.characterName.text = quote.character
        holder.quoteView.quote.text = quote.quote

    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    class QuotesListHolder(val quoteView: QuoteItemBinding): RecyclerView.ViewHolder(quoteView.root)
}