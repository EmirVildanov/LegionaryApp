package com.example.legionaryapp.data

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class Task(
    val name: String,
    val deadline: Int,
    val managerName: String,
)