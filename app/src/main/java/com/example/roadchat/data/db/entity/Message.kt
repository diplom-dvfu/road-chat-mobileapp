package com.example.roadchat.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "message")
data class Message(
    @PrimaryKey(autoGenerate = true) val messageId: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "text") val text: String,
    @ColumnInfo(name = "interlocutor_name") val interlocutorName: String,
    @ColumnInfo(name = "sender_name") val senderName: String,
    @ColumnInfo(name = "datetime") val datetime: Int
)