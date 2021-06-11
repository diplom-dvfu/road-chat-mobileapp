package com.example.roadchat.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

//
@Entity
data class Plate(
    @PrimaryKey protected val id: Int,
    protected val number: String
)

@Entity
data class User(
    @PrimaryKey protected val id: Int,
    protected val login: String,
    protected val isDriver: Boolean,
    protected val plates: List<Plate>
)

