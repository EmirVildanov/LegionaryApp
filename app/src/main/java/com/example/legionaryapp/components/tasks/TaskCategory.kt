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
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.data.filterByCategory
import com.example.legionaryapp.data.filterByDeadlineType
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.ui.theme.Grey

enum class DeadlineInterval(val deadlineName: String) {
    WeekTab("Ближайшие"),
    MonthTab("Месяц"),
    ThreeMonthTab("Три месяца")
}

@Composable
fun TaskCategory(category: Category) {
    val currentTab = remember {
        mutableStateOf(DeadlineInterval.WeekTab)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionHeader(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(),
            progress = 0.5f,
            title = listOf(category.name),
            subtitle = category.description,
            titleTopPadding = 30.dp
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
            myTasks = UserRepository.myTasks.value.filterByCategory(category)
                .filterByDeadlineType(DeadlineInterval.WeekTab),
            includeHeader = false
        )
    }
}

@Composable
fun DeadlineIntervalTabs(
    currentTab: DeadlineInterval,
    onTabSelection: (DeadlineInterval) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (value in DeadlineInterval.values()) {
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
    deadlineInterval: DeadlineInterval,
    onClick: (DeadlineInterval) -> Unit,
    currentTab: DeadlineInterval
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
