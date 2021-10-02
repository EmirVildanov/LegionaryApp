package com.example.legionaryapp.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.R
import com.example.legionaryapp.navigation.LegionaryScreen
import java.util.Locale


@Composable
fun LegionaryTabRow(
    allScreens: List<LegionaryScreen>,
    onTabSelected: (LegionaryScreen) -> Unit,
    currentScreen: LegionaryScreen
) {
    Surface(
        Modifier
            .height(TabHeight)
            .fillMaxWidth()
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            allScreens.forEach { screen ->
                LegionaryTab(
                    text = screen.name,
                    iconId = screen.iconId,
                    onSelected = { onTabSelected(screen) },
                    selected = currentScreen == screen
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
    selected: Boolean
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .height(TabHeight)
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            )
            .clearAndSetSemantics { contentDescription = text }
    ) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier
                .size(35.dp)
                .clip(CircleShape),
            colorFilter = if (selected) ColorFilter.tint(Color.Black) else null
        )
        if (selected) {
//            Spacer(Modifier.width(12.dp))
            Text(text.uppercase(Locale.getDefault()))
        }
    }
}

private val TabHeight = 60.dp
