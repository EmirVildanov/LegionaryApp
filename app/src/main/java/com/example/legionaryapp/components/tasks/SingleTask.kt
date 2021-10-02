package com.example.legionaryapp.components.tasks

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.legionaryapp.data.Task

@Composable
fun SingleTask(task: Task) {
    Text(task.name)
    Text("Manager: ${task.managerName}")
    Text("Deadline: ${task.deadline}")
}