package com.example.roadchat.data

import androidx.lifecycle.LiveData
import com.example.roadchat.data.model.db.Plate
import com.example.roadchat.data.model.db.User
import com.example.roadchat.data.model.db.UserDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
//    private val serverConnection:,
    private val userDao: UserDao
) {

    fun getUser(userId: Int): LiveData<User> {
        refreshUser(userId)
        return userDao.load(userId)
    }

    private fun refreshUser(userId: Int) {
        val user = User(0, "test", true, listOf(Plate(0, "У502НК125")))
        userDao.save(user)
    }
}