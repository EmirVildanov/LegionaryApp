package com.example.legionaryapp.data

import kotlinx.coroutines.delay

object EventsRepository {
    var events: List<Event> = emptyList()
        private set

    suspend fun fetchEvents() {
        delay(100)
        events = listOf(Event(id = 0, title = "Yeah", description = "Nice"))
    }

    suspend fun postEvent(title: String, description: String) {
        delay(100)
        events = events + listOf(
            Event(
                id = events.size,
                title = title,
                description = description
            )
        )
    }
}

data class Event(val id: Int, val title: String, val description: String)
