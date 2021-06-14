package com.example.roadchat.data.db.repository

import androidx.annotation.WorkerThread
import com.example.roadchat.data.db.dao.UserDao
import com.example.roadchat.data.db.entity.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val user: Flow<User> = userDao.getUserInfo()

    val userAccounts: Flow<List<User>> = userDao.getUserAccounts()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(chat: User) {
        userDao.insert(chat)
    }
}