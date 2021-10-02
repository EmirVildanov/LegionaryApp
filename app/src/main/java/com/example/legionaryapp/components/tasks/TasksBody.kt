package com.example.legionaryapp.components.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.R
import com.example.legionaryapp.data.Task
import com.example.legionaryapp.data.User
import com.example.legionaryapp.data.UserRepository

@Composable
fun TasksBody(
    tasks: List<Task>,
) {
    val progress = remember { mutableStateOf(0) }

    Column {
        TasksHeader(modifier = Modifier.weight(2f), progress = 1f)
        InterestTasks(modifier = Modifier.weight(1f))
        DeadlineTasks(modifier = Modifier.weight(2f))
    }
}

@Composable
fun TasksHeader(modifier: Modifier, progress: Float) {
    Card {
        Image(
            painter = painterResource(id = R.drawable.blue_gradient_background),
            contentDescription = "gradientBackground"
        )
        Column(modifier = modifier) {
            Text("Добро пожаловать", color = Color.White, fontWeight = FontWeight.Bold)
            Text("в e-Legion", color = Color.White)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Прогесс", color = Color.White, fontWeight = FontWeight.Bold)
            Row {
                LinearProgressIndicator(progress = progress)
                Text("$progress%", color = Color.White)
            }
        }
    }
}

@Composable
fun InterestTasks(modifier: Modifier) {
    LazyRow{
        items(UserRepository.tasks) { task ->
            Text("Test")
        }
    }
}

@Composable
fun DeadlineTasks(modifier: Modifier) {

}