package com.example.roadchat.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roadchat.data.db.entity.Chat
import com.example.roadchat.data.db.entity.Message
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {

    @Query("SELECT * FROM message where username=:username and interlocutor_name=:interlocutorName order by messageId")
    fun getMessages(username: String, interlocutorName: String): Flow<List<Message>>

    // The flow always holds/caches latest version of data. Notifies its observers when the
    // data has changed.
    @Query("SELECT * FROM chats_table ORDER BY id ASC")
    fun getChats(): Flow<List<Chat>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: Chat)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMessage(message: Message)


    @Query("DELETE FROM chats_table")
    suspend fun deleteAll()
}