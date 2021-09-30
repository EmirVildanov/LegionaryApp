package com.example.legionaryapp.fragments.welcome

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

@Composable
fun MainMenuScreen(texts: List<String> = listOf("Line1", "Line2", "Line3", "Line4")) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        Text("Welcome")
        Column(modifier = Modifier.weight(1f)) {
            for (i in 0..30) {
                FormattedText(text = "Line$i")
                Divider(color = Color.Black)
            }
        }
        Column {
            Counter(
                count = counterState.value,
                updateCount = { newCount ->
                    counterState.value = newCount
                }
            )
        }
    }
}

@Composable
fun FormattedText(text: String) {
    Text(text = text, modifier = Modifier.padding(4.dp))
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    OutlinedButton(onClick = { updateCount(count + 1) }) {
        Text("I've been clicked $count times")
    }
}

@Preview
@Composable
fun MainMenuScreenPreview() {
    LegionaryAppTheme {
        MainMenuScreen()
    }
}