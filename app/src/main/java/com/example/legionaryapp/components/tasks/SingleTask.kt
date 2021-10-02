package com.example.legionaryapp.components.tasks

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.legionaryapp.network.Task

@Composable
fun SingleTask(task: Task) {
    Text(task.title)
    Text("Description: ${task.description}")
    Text("Deadline: ${task.deadlineType.shortName}")
}