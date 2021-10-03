package com.example.legionaryapp.components.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.data.EventsRepository
import kotlinx.coroutines.runBlocking

@Preview
@Composable
fun AddEventForm() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            maxLines = 1
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            maxLines = 2,
            modifier = Modifier.padding(20.dp)
        )


        OutlinedButton(onClick = {
            runBlocking {
                EventsRepository.postEvent(title, description)
            }
            title = ""
            description = ""
        }) {
            Text("Опубликовать")
        }
    }
}