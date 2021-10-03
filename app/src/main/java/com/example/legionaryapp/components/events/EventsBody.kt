package com.example.legionaryapp.components.events

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
import com.example.legionaryapp.components.tasks.*
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.ui.theme.Grey


enum class EventTabEnum(val eventName: String) {
    AllEventsTab("Все мероприятия"),
    AddEventTab("Добавить"),
}

@Composable
fun EventsBody() {

    val currentTab = remember {
        mutableStateOf(EventTabEnum.AllEventsTab)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        SectionHeader(
            modifier = Modifier
                .weight(4f)
                .fillMaxWidth(),
            progress = 0f,
            showingProgress = false,
            title = listOf("Новости"),
            subtitle = "\n" +
                    "Кто-то решил собраться поиграть в футбол или волейбол? Хотите узнать когда у коллеги день рождения? Здесь вы можете следить за всеми новостями e-Legion",
            titleTopPadding = 40.dp,
            imageId = R.drawable.circle,
            imageModifier = Modifier
                .size(5.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        EventTabsRow(
            currentTab = currentTab.value,
            onTabSelection = { tab -> currentTab.value = tab })
        Spacer(modifier = Modifier.height(30.dp))
        if (currentTab.value == EventTabEnum.AllEventsTab) {
            DeadlineTasks(
                modifier = Modifier
                    .weight(4f)
                    .padding(15.dp)
                    .fillMaxWidth(),
                myTasks = UserRepository.myTasks.value,
                includeHeader = false
            )
        } else {
            Column(
                modifier = Modifier
                    .weight(4f)
                    .padding(15.dp)
                    .fillMaxWidth()
            ) {
                Text("Add")
            }
        }
    }
}

@Composable
fun EventTabsRow(
    currentTab: EventTabEnum,
    onTabSelection: (EventTabEnum) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        for (value in EventTabEnum.values()) {
            EventTab(
                eventTabEnum = value,
                onClick = onTabSelection,
                currentTab = currentTab
            )
        }
    }
}

@Composable
fun EventTab(
    eventTabEnum: EventTabEnum,
    onClick: (EventTabEnum) -> Unit,
    currentTab: EventTabEnum
) {
    if (currentTab == eventTabEnum) {
        Text(
            text = eventTabEnum.eventName,
            color = Color.Black,
            modifier = Modifier.clickable { onClick(eventTabEnum) })
    } else {
        Text(
            text = eventTabEnum.eventName,
            color = Grey,
            modifier = Modifier.clickable { onClick(eventTabEnum) })
    }
}
