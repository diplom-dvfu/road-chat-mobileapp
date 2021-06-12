package com.example.roadchat

import android.app.Application
import com.example.roadchat.data.model.db.ChatsDatabase
import com.example.roadchat.data.model.db.ChatsRepository
import com.example.roadchat.data.model.db.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RoadChatApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val chatsDatabase by lazy { ChatsDatabase.getDatabase(this, applicationScope) }
    val chatsRepository by lazy { ChatsRepository(chatsDatabase.ChatDao()) }
    val userRepository by lazy { UserRepository(chatsDatabase.UserDao()) }
}