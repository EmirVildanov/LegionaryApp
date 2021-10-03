package com.example.legionaryapp.data

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.legionaryapp.network.DeadlineType
import kotlinx.coroutines.delay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object EventsRepository {
    var events: MutableState<List<Event>> = mutableStateOf(emptyList())
        private set

    suspend fun fetchEvents() {
        delay(100)
        events.value = listOf(
            Event(id = 0, title = "Yeah", description = "Nice", eventDeadlineType = EventDeadlineType.Week),
            Event(id = 1, title = "Go play bideo games", description = "The Sims 2", eventDeadlineType = EventDeadlineType.Today)
        )
    }

    suspend fun postEvent(title: String, description: String) {
        delay(100)
        events.value = events.value + listOf(
            Event(
                id = (0..10000).random(),
                title = title,
                description = description
            )
        )
    }

    suspend fun removeEvent(eventId: Int) {
        delay(100)
        events.value = events.value.filter { it.id != eventId }
    }
}

@Serializable
@Immutable
data class Event(
    val id: Int,
    val title: String,
    val description: String,
    @SerialName("event_deadline_type") val eventDeadlineType: EventDeadlineType = EventDeadlineType.Week
)

fun List<Event>.sortedByDeadline() = sortedBy { it.eventDeadlineType.daysNumber }


@Serializable
enum class EventDeadlineType(val daysNumber: Int) {
    @SerialName("0")
    Today(0) {
        override fun toString(): String = "Сегодня"
    },

    @SerialName("1")
    Tomorrow(1) {
        override fun toString(): String = "Завтра"
    },

    @SerialName("7")
    Week(7) {
        override fun toString(): String = "На неделе"
    },

    @SerialName("30")
    Month(30) {
        override fun toString(): String = "В этом месяце"
    },

    @SerialName("31")
    More(31) {
        override fun toString(): String = "Когда-то"
    },
}
