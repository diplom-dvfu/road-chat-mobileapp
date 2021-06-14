package com.example.roadchat.data.db.repository

import androidx.annotation.WorkerThread
import com.example.roadchat.data.db.dao.ChatDao
import com.example.roadchat.data.db.entity.Chat
import com.example.roadchat.data.db.entity.Message
import kotlinx.coroutines.flow.Flow


class ChatsRepository(private val chatDao: ChatDao) {

    val allChats: Flow<List<Chat>> = chatDao.getChats()

    fun getMessages(username: String, interlocutorName: String): Flow<List<Message>> {
        return chatDao.getMessages(username, interlocutorName)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(chat: Chat) {
        chatDao.insert(chat)
    }
}