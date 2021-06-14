package com.example.roadchat

import android.app.Application
import com.example.roadchat.data.db.ChatsDatabase
import com.example.roadchat.data.db.repository.ChatsRepository
import com.example.roadchat.data.db.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class RoadChatApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())


    private lateinit var chatsDatabase: ChatsDatabase
    lateinit var chatsRepository: ChatsRepository
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        chatsDatabase = ChatsDatabase.getDatabase(this, applicationScope)
        chatsRepository = ChatsRepository(chatsDatabase.ChatDao())
        userRepository = UserRepository(chatsDatabase.UserDao())
    }


}