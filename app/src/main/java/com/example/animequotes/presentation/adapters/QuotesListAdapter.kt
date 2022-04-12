package com.example.animequotes.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.animequotes.R
import com.example.animequotes.databinding.QuoteItemBinding
import com.example.animequotes.databinding.QuoteItemLikeBinding
import com.example.animequotes.domain.entities.Quote
import java.lang.RuntimeException
import kotlin.coroutines.coroutineContext

class QuotesListAdapter(val context: Context): RecyclerView.Adapter<QuotesListAdapter.QuotesListHolder>() {

    var onClickListener: ((Quote) -> (Unit))? = null
    var onTipClickListener: ((Quote) -> (Unit))? = null
    var quotesList = listOf<Quote>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesListHolder {
        return QuotesListHolder(QuoteItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false))
    }

    override fun onBindViewHolder(holder: QuotesListHolder, position: Int) {
        val quote = quotesList[position]
        holder.apply {
            quoteView.animeName.text = quote.anime
            quoteView.characterName.text = quote.character
            quoteView.quote.text = quote.quote
            quoteView.likeIcon.setOnClickListener {
                onClickListener?.invoke(quote)
                it.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotation))
            }
            quoteView.infoIcon.setOnClickListener {
            }
        }
    }


    override fun getItemCount(): Int {
        return quotesList.size
    }

    class QuotesListHolder(val quoteView: QuoteItemBinding): RecyclerView.ViewHolder(quoteView.root)
}