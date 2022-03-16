package com.example.animequotes.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    val anime: String,
    val character: String,
    val quote: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
