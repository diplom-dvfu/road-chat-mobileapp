package com.example.roadchat.ui.all_accounts

import androidx.lifecycle.*
import com.example.roadchat.data.db.entity.User
//import com.example.roadchat.data.db.UserAccounts
import com.example.roadchat.data.db.repository.UserRepository
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