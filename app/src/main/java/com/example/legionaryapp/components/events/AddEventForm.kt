package com.example.legionaryapp.components.news

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.data.EventsRepository
import com.example.legionaryapp.ui.theme.Grey
import kotlinx.coroutines.runBlocking

@Preview
@Composable
fun AddEventForm() {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Название") },
            maxLines = 1
        )
        
        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Описание") },
            maxLines = 2,
            modifier = Modifier.height(100.dp)
        )

        Spacer(modifier = Modifier.height(15.dp))

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