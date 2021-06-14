package com.example.roadchat.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roadchat.data.db.entity.Chat
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM chats_table ORDER BY id ASC")
    fun getChats(): Flow<List<Chat>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Chat)

    @Query("DELETE FROM chats_table")
    suspend fun deleteAll()
}