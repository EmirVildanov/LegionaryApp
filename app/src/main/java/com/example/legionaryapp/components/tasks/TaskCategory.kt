package com.example.legionaryapp.components.tasks

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.R
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.data.filterByCategory
import com.example.legionaryapp.data.filterByDeadlineType
import com.example.legionaryapp.data.progress
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.DeadlineType
import com.example.legionaryapp.ui.theme.Grey

enum class DeadlineTab(val deadlineName: String, val deadlineType: DeadlineType) {
    WeekTab("Ближайшие", DeadlineType.Week),
    MonthTab("Месяц", DeadlineType.Month),
    ThreeMonthTab("Три месяца", DeadlineType.Full)
}

@Composable
fun TaskCategory(category: Category) {
    val currentTab = remember {
        mutableStateOf(DeadlineTab.WeekTab)
    }

    val myTasks = remember { UserRepository.myTasks }

    val tasksByCategory = myTasks.value.filterByCategory(category)

    Column(modifier = Modifier.fillMaxSize()) {
        SectionHeader(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            progress = tasksByCategory.progress().toFloat(),
            title = listOf(category.name),
            subtitle = category.description,
            titleTopPadding = 30.dp,
            imageId = R.drawable.flower,
            imageModifier = Modifier
                .size(5.dp)
                .offset(x = (50).dp, y = (-50).dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        DeadlineIntervalTabs(
            currentTab = currentTab.value,
            onTabSelection = { tab -> currentTab.value = tab })
        DeadlineTasks(
            modifier = Modifier
                .weight(4f)
                .padding(15.dp)
                .fillMaxWidth(),
            myTasks = tasksByCategory.filterByDeadlineType(currentTab.value),
            includeHeader = false
        )
    }
}

@Composable
fun DeadlineIntervalTabs(
    currentTab: DeadlineTab,
    onTabSelection: (DeadlineTab) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (value in DeadlineTab.values()) {
            DeadLineIntervalTab(
                deadlineInterval = value,
                onClick = onTabSelection,
                currentTab = currentTab
            )
        }
    }
}

@Composable
fun DeadLineIntervalTab(
    deadlineInterval: DeadlineTab,
    onClick: (DeadlineTab) -> Unit,
    currentTab: DeadlineTab
) {
    if (currentTab == deadlineInterval) {
        Text(
            text = deadlineInterval.deadlineName,
            color = Color.Black,
            modifier = Modifier.clickable { onClick(deadlineInterval) })
    } else {
        Text(
            text = deadlineInterval.deadlineName,
            color = Grey,
            modifier = Modifier.clickable { onClick(deadlineInterval) })
    }
}
