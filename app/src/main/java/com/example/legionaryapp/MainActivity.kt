package com.example.legionaryapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.legionaryapp.components.welcome.WelcomeScreen
import com.example.legionaryapp.data.EventsRepository
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.network.RestException
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        runBlocking {
            try {
                UserRepository.signIn(0)
                UserRepository.fetchEverything()
                EventsRepository.fetchEvents()
            } catch (e: RestException) {
                Timber.Forest.log(1, e)
            }
        }
        setContent {
            WelcomeScreen()
        }
    }
}