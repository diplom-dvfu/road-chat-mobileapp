package com.example.roadchat.data.model

import androidx.lifecycle.*
import com.example.roadchat.data.model.db.User
//import com.example.roadchat.data.model.db.UserAccounts
import com.example.roadchat.data.model.db.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val user: LiveData<User> = repository.user.asLiveData()

    val userAccounts: LiveData<List<User>> = repository.userAccounts.asLiveData()

    fun insert(word: User) = viewModelScope.launch {
        repository.insert(word)
    }
}

class UserViewModelFactory(private val repository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}