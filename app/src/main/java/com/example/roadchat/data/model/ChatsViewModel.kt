package com.example.roadchat.data.model

import androidx.lifecycle.*
import com.example.roadchat.data.model.db.Chat
import com.example.roadchat.data.model.db.ChatsRepository
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