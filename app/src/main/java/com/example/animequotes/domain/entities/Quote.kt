package com.example.animequotes.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cached_quotes")
data class Quote(
    val anime: String,
    val character: String,
    val quote: String,
    @ColumnInfo(name = "is_favourite")var isFavourite: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)