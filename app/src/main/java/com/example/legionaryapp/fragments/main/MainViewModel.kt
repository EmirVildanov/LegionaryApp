package com.example.legionaryapp.fragments.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.navigation.Screen
import com.example.legionaryapp.util.CustomEvent
import timber.log.Timber

class MainViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _navigateTo = MutableLiveData<CustomEvent<Screen>>()
    val navigateTo: LiveData<CustomEvent<Screen>> = _navigateTo

    fun handleContinue(email: String) {
        Timber.i("Handle continue")
    }

    fun signInAsGuest() {
        Timber.i("Sign in as guest")
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}