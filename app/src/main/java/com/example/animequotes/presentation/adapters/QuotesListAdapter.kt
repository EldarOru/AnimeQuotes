package com.example.animequotes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.animequotes.databinding.QuoteItemBinding
import com.example.animequotes.databinding.QuoteItemFavBinding
import com.example.animequotes.domain.entities.Quote
import java.lang.RuntimeException

class QuotesListAdapter: RecyclerView.Adapter<QuotesListAdapter.QuotesListHolder>() {

    var onClickListener: ((Quote) -> (Unit))? = null
    var quotesList = listOf<Quote>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesListHolder {
        return when(viewType){
                LIKED_QUOTE -> QuotesListHolder.QuotesWithLikeViewHolder(
                    QuoteItemFavBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false))
                NOT_LIKED_QUOTE -> QuotesListHolder.QuotesWithOutLikeViewHolder(
                    QuoteItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false))
                else -> throw RuntimeException("Unknown view type: $viewType")
            }

    }

    override fun onBindViewHolder(holder: QuotesListHolder, position: Int) {
        val quote = quotesList[position]
        when(holder){
            //TODO CHANGE
            is QuotesListHolder.QuotesWithOutLikeViewHolder -> {
                holder.apply {
                    binding.animeName.text = quote.anime
                    binding.characterName.text = quote.character
                    binding.quote.text = quote.quote
                    binding.likeIcon.setOnClickListener {
                        onClickListener?.invoke(quote)
                    }
                }
            }
            is QuotesListHolder.QuotesWithLikeViewHolder -> {
                holder.apply {
                    binding.animeName.text = quote.anime
                    binding.characterName.text = quote.character
                    binding.quote.text = quote.quote
                    binding.likeIcon.setOnClickListener {
                        onClickListener?.invoke(quote)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return quotesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (quotesList[position].isFavourite){
            LIKED_QUOTE
        }else NOT_LIKED_QUOTE
    }

    companion object {
        const val LIKED_QUOTE = 1
        const val NOT_LIKED_QUOTE = 2
    }

    sealed class QuotesListHolder(quoteView: ViewBinding): RecyclerView.ViewHolder(quoteView.root){

        class QuotesWithLikeViewHolder(val binding: QuoteItemFavBinding) : QuotesListHolder(binding)

        class QuotesWithOutLikeViewHolder(val binding: QuoteItemBinding) : QuotesListHolder(binding)
    }
}