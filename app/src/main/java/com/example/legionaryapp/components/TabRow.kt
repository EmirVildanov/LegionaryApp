package com.example.legionaryapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.legionaryapp.navigation.LegionaryScreen
import com.example.legionaryapp.ui.theme.Grey
import com.example.legionaryapp.ui.theme.LegionaryAppTheme
import java.util.Locale


@Composable
fun LegionaryTabRow(
    tabScreens: List<LegionaryScreen>,
    onTabSelected: (LegionaryScreen) -> Unit,
    selectedTab: LegionaryScreen
) {
    Surface(
        Modifier
            .height(TabHeight)
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            tabScreens.forEach { screen ->
                LegionaryTab(
                    text = screen.screenName,
                    onSelected = { onTabSelected(screen) },
                    isSelected = selectedTab == screen,
                    iconId = if (selectedTab == screen) screen.blackIconId else screen.greyIconId
                )
            }
        }
    }
}

@Composable
private fun LegionaryTab(
    text: String,
    iconId: Int,
    onSelected: () -> Unit,
    isSelected: Boolean
) {
    Column(
        modifier = Modifier
            .height(TabHeight)
            .selectable(
                selected = isSelected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape),
            colorFilter = if (isSelected) ColorFilter.tint(Color.Black) else null
        )
        if (isSelected) {
            Text(text = text.uppercase(Locale.getDefault()), color = Color.Black, fontSize = 10.sp)
        } else {
            Text(text = text.uppercase(Locale.getDefault()), color = Grey, fontSize = 10.sp)
        }
    }
}

@Preview
@Composable
fun LegionaryTabRowPreview() {
    val selectedTab = remember { mutableStateOf(LegionaryScreen.Tasks) }

    LegionaryAppTheme {
        LegionaryTabRow(
            tabScreens = listOf(
                LegionaryScreen.News,
                LegionaryScreen.Tasks,
                LegionaryScreen.Guide
            ),
            onTabSelected = { screen ->
                selectedTab.value = screen
            },
            selectedTab = selectedTab.value
        )
    }
}

private val TabHeight = 80.dp
