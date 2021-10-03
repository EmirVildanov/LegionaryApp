package com.example.legionaryapp.components.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legionaryapp.R
import com.example.legionaryapp.data.Event
import com.example.legionaryapp.data.EventsRepository
import kotlinx.coroutines.runBlocking
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import com.example.legionaryapp.data.sortedByDeadline

@Composable
fun EventsHolder(
    modifier: Modifier,
    events: List<Event>,
    includeHeader: Boolean = true
) {
    Column(modifier = modifier) {
        if (includeHeader) {
            Text(text = "Ближайшие задачи", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(events.sortedByDeadline()) { event ->
                EventCard(event)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun EventCard(event: Event) {
    val color = MaterialTheme.colors.primary

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(
                width = 2.dp,
                color = color,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.height(33.dp),
            text = event.title, fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier.height(33.dp),
            text = event.description, fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.body1.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant),
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .background(color)
                    .padding(5.dp),
                onClick = {
//                    runBlocking {
//                        EventsRepository.updateTaskStatus(task.id, !task.isComplete)
//                    }
                },
            ) {
                Text("Я пойду")
            }
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(R.drawable.alarm_blue_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = event.eventDeadlineType.toString(),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = MaterialTheme.colors.onSurface
            )
            OutlinedButton(
                onClick = {
                    runBlocking {
                        EventsRepository.removeEvent(event.id)
                    }
                },
            ) {
                Text("X")
            }
        }
    }

}
