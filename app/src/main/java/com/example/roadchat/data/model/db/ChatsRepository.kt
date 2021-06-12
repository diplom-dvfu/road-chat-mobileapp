package com.example.roadchat.data.model.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class ChatsRepository(private val chatDao: ChatDao) {

    val allChats: Flow<List<Chat>> = chatDao.getChats()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(chat: Chat) {
        chatDao.insert(chat)
    }
}