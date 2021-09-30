package com.example.legionaryapp.fragments.signUp

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

sealed class SignUpEvent {
    object SignIn : SignUpEvent()
    data class SignUp(val email: String, val password: String) : SignUpEvent()
    object SignInAsGuest : SignUpEvent()
    object NavigateBack : SignUpEvent()
}

@Composable
fun SignUpScreen(onNavigationEvent: (SignUpEvent) -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Text("Sign up")
    }
}