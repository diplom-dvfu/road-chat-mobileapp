package com.example.roadchat.data.model.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val user: Flow<User> = userDao.getUserInfo()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(chat: User) {
        userDao.insert(chat)
    }
}