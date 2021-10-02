package com.example.legionaryapp.components.tasks

import android.R.attr
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legionaryapp.R
import com.example.legionaryapp.ui.theme.LegionaryAppTheme
import androidx.compose.ui.text.style.TextOverflow

import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.example.legionaryapp.data.mockCategories
import com.example.legionaryapp.data.mockTasks
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.data.categories
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.Task


@Composable
fun TasksBody(
    myTasks: MutableState<List<Task>>,
    onCategoryClick: (Int) -> Unit
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
        Categories(
            modifier = Modifier
                .weight(2f)
                .padding(15.dp)
                .fillMaxWidth(),
            onCategoryClick = onCategoryClick
        )
        DeadlineTasks(
            modifier = Modifier
                .weight(3f)
                .padding(15.dp)
                .fillMaxWidth(),
            myTasks = myTasks
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
fun Categories(
    modifier: Modifier,
    onCategoryClick: (Int) -> Unit
) {
    val selectedCategory = remember {
        mutableStateOf(
            Category(
                1,
                "Офис",
                "Список курсов, включающих в себя задачи по адаптации к работе в офисе и формированию комфортного рабочего пространства"
            )
        )
    }

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
            items(mockCategories) { category ->
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
            .shadow(4.dp, shape = RoundedCornerShape(26.dp), clip = true)
            .border(
                width = 2.dp,
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(25.dp)
            )
            .background(if (selectedCategory.value == category) MaterialTheme.colors.primary else Color.Transparent)
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
            color = if (selectedCategory.value == category) Color.White else Color.Black
        )
        if (selectedCategory.value == category) {
            Button(onClick = { }) {
                Text("Открыть")
            }
        }
    }
}

@Composable
fun DeadlineTasks(
    modifier: Modifier,
    myTasks: MutableState<List<Task>>
) {
    Column(modifier = modifier) {
        Text(text = "Ближайшие задачи", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(myTasks.value) { task ->
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
            .height(150.dp)
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = task.title,
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
                fontSize = MaterialTheme.typography.h5.fontSize,
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}

@Preview
@Composable
fun TasksBodyPreview() {
    LegionaryAppTheme {
        TasksBody(myTasks = mutableStateOf(mockTasks), onCategoryClick = {})
    }
}