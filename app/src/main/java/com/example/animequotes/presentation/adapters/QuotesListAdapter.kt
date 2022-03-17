package com.example.animequotes.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.animequotes.R
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
                ENABLED_NUM -> QuotesListHolder.QuotesWithLikeViewHolder(
                    QuoteItemFavBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent, false))
                DISABLED_NUM -> QuotesListHolder.QuotesWithOutLikeViewHolder(
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

    //определяет enable у shopItem, на основании которого в onCreateViewHolder выбирается макет
    override fun getItemViewType(position: Int): Int {
        return if (quotesList[position].isFavourite){
            ENABLED_NUM
        }else DISABLED_NUM
    }

    companion object {
        const val ENABLED_NUM = 1
        const val DISABLED_NUM = 2
    }

    sealed class QuotesListHolder(quoteView: ViewBinding): RecyclerView.ViewHolder(quoteView.root){
        class QuotesWithLikeViewHolder(val binding: QuoteItemFavBinding) : QuotesListHolder(binding)

        class QuotesWithOutLikeViewHolder(val binding: QuoteItemBinding) : QuotesListHolder(binding)
    }
}