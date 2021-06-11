package com.example.roadchat.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "chats_table")
//data class Chat(
//    @PrimaryKey @ColumnInfo(name = "word") val word: String
//)

@Entity(tableName = "chats_table")
data class Chat(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username") val username: String,
    @ColumnInfo(name = "last_msg") val lastMessage: String,
    @ColumnInfo(name = "last_date") val lastMessageDate: String
)
