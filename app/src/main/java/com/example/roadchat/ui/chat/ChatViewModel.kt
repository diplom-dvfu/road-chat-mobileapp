package com.example.roadchat.ui.chat

import androidx.lifecycle.*
import com.example.roadchat.data.db.entity.Message
import com.example.roadchat.data.db.repository.ChatsRepository
import kotlinx.coroutines.launch


class ChatViewModel(
    private val username: String, private val interlocutorName: String,
    private val repository: ChatsRepository
) : ViewModel() {

    val messages: LiveData<List<Message>> =
        repository.getMessages(username, interlocutorName).asLiveData()

    fun insert(message: Message) = viewModelScope.launch {
//        repository.insert(message)
    }

//    fun sendMessage(message: Message) : {
//
//    }
}

class ChatViewModelFactory(
    private val username: String,
    private val interlocutorName: String,
    private val repository: ChatsRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatViewModel(username, interlocutorName, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}