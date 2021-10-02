package com.example.legionaryapp.components.news

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.components.tasks.DeadlineTasks
import com.example.legionaryapp.components.tasks.SectionHeader
import com.example.legionaryapp.data.UserRepository

@Composable
fun NewsBody() {
    Column(modifier = Modifier.fillMaxSize()) {
        SectionHeader(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth(),
            progress = 0f,
            showingProgress = false,
            title = listOf("Новости"),
            subtitle = "\n" +
                    "Кто-то решил собраться поиграть в футбол или волейбол? Хотите узнать когда у коллеги день рождения? Здесь вы можете следить за всеми новостями e-Legion",
            titleTopPadding = 40.dp
        )
        Spacer(modifier = Modifier.height(30.dp))
        DeadlineTasks(
            modifier = Modifier
                .weight(4f)
                .padding(15.dp)
                .fillMaxWidth(),
            myTasks = UserRepository.myTasks.value,
            includeHeader = false
        )
    }
}