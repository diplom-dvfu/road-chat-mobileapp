package com.example.roadchat.data.model.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "username") val username: String
)

//@Entity
//data class Playlist(
//    @PrimaryKey val playlistId: Long,
//    val userCreatorId: Long,
//    val playlistName: String
//)