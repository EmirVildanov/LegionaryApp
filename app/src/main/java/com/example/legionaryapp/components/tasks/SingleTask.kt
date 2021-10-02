package com.example.legionaryapp.components.tasks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.Task

@Composable
fun TaskCategory(category: Category) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(category.name)
        Text("Description: ${category.description}")
    }
}