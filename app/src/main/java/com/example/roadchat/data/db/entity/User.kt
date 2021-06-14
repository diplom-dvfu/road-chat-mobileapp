package com.example.roadchat.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Int,
    val username: String
)
