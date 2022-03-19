package com.example.animequotes.domain.entities


data class Quote(
    val anime: String,
    val character: String,
    val quote: String,
    var isFavourite: Boolean = false
)