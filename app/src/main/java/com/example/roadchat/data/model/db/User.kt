package com.example.roadchat.data.model.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Int,
    val username: String
)
