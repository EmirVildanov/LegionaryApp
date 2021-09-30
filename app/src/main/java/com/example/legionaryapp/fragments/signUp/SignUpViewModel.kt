package com.example.legionaryapp.fragments.signUp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.navigation.Screen
import com.example.legionaryapp.navigation.Screen.Main
import com.example.legionaryapp.navigation.Screen.Welcome
import com.example.legionaryapp.util.CustomEvent

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val _navigateTo = MutableLiveData<CustomEvent<Screen>>()
    val navigateTo: LiveData<CustomEvent<Screen>>
        get() = _navigateTo

    /**
     * Consider all sign ups successful
     */
    fun signUp(email: String, password: String) {
        userRepository.signUp(email, password)
        _navigateTo.value = CustomEvent(Main)
    }

    fun signInAsGuest() {
        userRepository.signInAsGuest()
        _navigateTo.value = CustomEvent(Main)
    }

    fun signIn() {
        _navigateTo.value = CustomEvent(Welcome)
    }
}

@Suppress("UNCHECKED_CAST")
class SignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            return SignUpViewModel(UserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}