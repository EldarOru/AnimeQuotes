package com.example.animequotes.domain.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "quotes")
data class QuoteDatabaseModel(
    val anime: String,
    val character: String,
    val quote: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)
