package com.example.roadchat.ui.chats_preview

import androidx.lifecycle.*
import com.example.roadchat.data.db.entity.Chat
import com.example.roadchat.data.db.repository.ChatsRepository
import kotlinx.coroutines.launch


class ChatsPreviewViewModel(private val repository: ChatsRepository) : ViewModel() {

    val allChats: LiveData<List<Chat>> = repository.allChats.asLiveData()

    fun insert(word: Chat) = viewModelScope.launch {
        repository.insert(word)
    }
}

class ChatsPreviewViewModelFactory(private val repository: ChatsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChatsPreviewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ChatsPreviewViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}