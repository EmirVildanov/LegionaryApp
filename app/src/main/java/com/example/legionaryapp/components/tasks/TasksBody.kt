package com.example.legionaryapp.components.tasks

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legionaryapp.R
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.network.Task
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

@Composable
fun TasksBody() {
    val progress = remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TasksHeader(
            modifier = Modifier
                .weight(2f)
                .fillMaxWidth(),
            progress = progress.value
        )
        InterestTasks(
            modifier = Modifier
                .weight(1f)
                .padding(15.dp)
                .fillMaxWidth()
        )
        DeadlineTasks(
            modifier = Modifier
                .weight(2f)
                .padding(15.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TasksHeader(modifier: Modifier, progress: Float) {
    Card(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.blue_gradient_background),
            contentDescription = "gradientBackground",
            modifier = Modifier.fillMaxSize()
        )
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(modifier = Modifier.weight(3f)) {
                Text(
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    text = "Добро пожаловать",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    text = "в e-Legion",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    text = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud amet.",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    text = "Прогесс",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(modifier = Modifier.weight(1f), progress = progress)
                    Text(fontSize = 15.sp, text = "${progress.toInt()}%", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun InterestTasks(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(
            text = "Задачи",
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.weight(1f)) {
            items(UserRepository.myTasks) { task ->
                InterestTaskCard(task)
            }
        }
    }
}

@Composable
fun InterestTaskCard(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(10.dp)
    ) {
        Text(text = task.title)
        Text(text = "Description: ${task.description}")
    }
}

@Composable
fun DeadlineTasks(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Ближайшие задачи", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(UserRepository.myTasks) { task ->
                DeadlineTaskCard(task)
            }
        }
    }
}

@Composable
fun DeadlineTaskCard(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(25.dp)
    ) {
        Text(text = task.title)
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = task.title)
            Text(text = task.title)
        }
    }
}

@Preview
@Composable
fun TasksBodyPreview() {
    LegionaryAppTheme {
        TasksBody()
    }
}