package com.example.legionaryapp.components.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.legionaryapp.ui.theme.LegionaryAppTheme
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.example.legionaryapp.data.categories
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.Task
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun TasksBody(
    onTaskCategoryClick: () -> Unit
) {
    val progress by remember { UserRepository.myProgress }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TasksHeader(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            progress = progress.toFloat()
        )
        InterestTasks(
            modifier = Modifier
                .weight(2f)
                .padding(15.dp)
                .fillMaxWidth()
        )
        DeadlineTasks(
            modifier = Modifier
                .weight(3f)
                .padding(15.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun TasksHeader(modifier: Modifier, progress: Float) {
    Card(modifier = modifier) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.blue_gradient_background),
            contentDescription = "gradientBackground",
            contentScale = ContentScale.Crop
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
                    text = "На данной странице отображен перечень курсов, которые необходимо пройти пройти в процессе адаптации и знакомства с e-Legion",
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
                    text = "Прогресс",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .weight(13f)
                            .height(20.dp),
                        progress = progress
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        modifier = Modifier.weight(2f),
                        fontSize = MaterialTheme.typography.subtitle1.fontSize,
                        text = "${progress.toInt()}%",
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
fun InterestTasks(modifier: Modifier) {
    val myTasks by remember { UserRepository.myTasks }

    Column(modifier = modifier) {
        Text(
            text = "Задачи",
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.weight(1f)) {
            items(myTasks.categories()) { category ->
                InterestTaskCard(category)
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
    }
}

@Composable
fun InterestTaskCard(category: Category) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .width(200.dp)
            .padding(10.dp),
    ) {
        Text(
            modifier = Modifier.height(33.dp),
            text = category.name,
            fontSize = MaterialTheme.typography.h6.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(text = category.description)
    }
}

@Composable
fun DeadlineTasks(modifier: Modifier) {
    val myTasks by remember { UserRepository.myTasks }

    Column(modifier = modifier) {
        Text(text = "Ближайшие задачи", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(myTasks) { task ->
                DeadlineTaskCard(task)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

@Composable
fun DeadlineTaskCard(task: Task) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .padding(25.dp)
    ) {
        Text(
            modifier = Modifier.height(33.dp),
            text = task.title, fontSize = MaterialTheme.typography.h6.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = task.title,
                color = Color.White,
                modifier = Modifier
                    .background(MaterialTheme.colors.primary)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
        }
        Text(text = "isComplete = ${task.isComplete}")
        OutlinedButton(onClick = {
            runBlocking {
                UserRepository.updateTaskStatus(task.id, !task.isComplete)
            }
        }) {
            Text("Update status")
        }
    }
}

@Preview
@Composable
fun TasksBodyPreview() {
    LegionaryAppTheme {
        TasksBody {

        }
    }
}