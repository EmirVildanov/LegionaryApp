package com.example.legionaryapp.components.tasks

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legionaryapp.R
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.data.categories
import com.example.legionaryapp.data.mockTasks
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.Task
import com.example.legionaryapp.ui.theme.LegionaryAppTheme
import kotlinx.coroutines.runBlocking


@Composable
fun TasksBody(
    myTasks: MutableState<List<Task>>,
    selectedCategory: MutableState<Category>,
    onCategoryClick: (Int) -> Unit
) {
    val progress by remember { UserRepository.myProgress }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SectionHeader(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            progress = progress.toFloat(),
            title = listOf("Добро пожаловать", "в e-Legion"),
            subtitle = "На данной странице отображен перечень курсов, которые необходимо пройти пройти в процессе адаптации и знакомства с e-Legion"
        )
        Categories(
            modifier = Modifier
                .weight(2f)
                .padding(15.dp)
                .fillMaxWidth(),
            onCategoryClick = onCategoryClick,
            myTasks = myTasks.value,
            selectedCategory = selectedCategory
        )
        DeadlineTasks(
            modifier = Modifier
                .weight(3f)
                .padding(15.dp)
                .fillMaxWidth(),
            myTasks = myTasks.value
        )
    }
}

@Composable
fun SectionHeader(
    modifier: Modifier,
    showingProgress: Boolean = true,
    progress: Float,
    title: List<String>,
    subtitle: String,
    titleTopPadding: Dp = 0.dp
) {
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
            Column(modifier = Modifier
                .weight(4.5f)
                .padding(top = titleTopPadding)) {
                for (text in title) {
                    Text(
                        fontSize = MaterialTheme.typography.h5.fontSize,
                        text = text,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    text = subtitle,
                    overflow = TextOverflow.Visible,
                    color = Color.White
                )
            }
            if (showingProgress) {
                Spacer(modifier = Modifier.height(6.dp))
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
                                .height(20.dp)
                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                ),
                            progress = progress / 100,
                            backgroundColor = Color.White
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
}

@Composable
fun Categories(
    modifier: Modifier,
    myTasks: List<Task>,
    onCategoryClick: (Int) -> Unit,
    selectedCategory: MutableState<Category>
) {
    Column(modifier = modifier) {
        Text(
            text = "Задачи",
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(modifier = Modifier.weight(1f)) {
            items(myTasks.categories()) { category ->
                TaskCategoryCard(
                    category = category,
                    onCategorySelection = {
                        selectedCategory.value = category
                        onCategoryClick(category.id)
                    },
                    selectedCategory = selectedCategory
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
        }
    }
}

@Composable
fun TaskCategoryCard(
    category: Category,
    onCategorySelection: (Category) -> Unit,
    selectedCategory: MutableState<Category>
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .background(
                color = if (selectedCategory.value == category) MaterialTheme.colors.primary else Color.Transparent,
                shape = RoundedCornerShape(25.dp)
            )
            .width(200.dp)
            .padding(13.dp)
            .clickable { onCategorySelection(category) },
    ) {
        Text(
            modifier = Modifier.height(33.dp),
            text = category.name,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = if (selectedCategory.value == category) Color.White else Color.Black
        )
        Text(
            text = category.description,
            fontSize = MaterialTheme.typography.caption.fontSize,
            overflow = TextOverflow.Visible,
            color = if (selectedCategory.value == category) Color.White else Color.Black
        )
    }
}

@Composable
fun DeadlineTasks(
    modifier: Modifier,
    myTasks: List<Task>,
    includeHeader: Boolean = true
) {
    Column(modifier = modifier) {
        if (includeHeader) {
            Text(text = "Ближайшие задачи", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
        }
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
            .padding(25.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.height(33.dp),
            text = task.title, fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (task.isComplete) "Выполнено" else "Не выполнено",
                color = Color.White,
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .background(MaterialTheme.colors.primary)
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Image(
                painter = painterResource(R.drawable.alarm_icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clip(CircleShape),
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primary)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Завтра",
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                color = MaterialTheme.colors.onSurface
            )
            OutlinedButton(onClick = {
                runBlocking {
                    UserRepository.updateTaskStatus(task.id, !task.isComplete)
                }
            }) {
                Text("*")
            }
        }
    }
}

@Preview
@Composable
fun TasksBodyPreview() {
    val myTasks by remember { mutableStateOf(UserRepository.myTasks) }
    val selectedCategory = remember {
        mutableStateOf(
            myTasks.value.first().category
        )
    }


    LegionaryAppTheme {
        TasksBody(myTasks = myTasks, selectedCategory = selectedCategory, onCategoryClick = {})
    }
}