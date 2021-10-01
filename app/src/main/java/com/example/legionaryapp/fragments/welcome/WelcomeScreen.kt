package com.example.legionaryapp.fragments.welcome

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.example.legionaryapp.R
import com.example.legionaryapp.ui.theme.*


@Composable
fun WelcomeScreen(texts: List<String> = List(100) { "Test string" }) {
    val counterState = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextList(
            texts = texts,
            modifier = Modifier
                .weight(1f)
        )
        Column(modifier = Modifier.padding(10.dp)) {
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
fun TextList(texts: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(items = texts) { item ->
            TextRow(text = item)
            Divider(color = MaterialTheme.colors.onPrimary)
        }
    }
}

@Composable
fun TextRow(text: String) {

    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) MaterialTheme.colors.onSecondary else MaterialTheme.colors.onPrimary)

    Row {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(painter = painterResource(R.drawable.avatar), contentDescription = "Person's avatar")
        }
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Name", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(
                    text = text,
                    modifier = Modifier
                        .background(color = backgroundColor)
                        .clickable(onClick = { isSelected = !isSelected })
                )
            }
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(backgroundColor = if (count > 5) Blue500 else Blue700)
    ) {
        Text("I've been clicked $count times")
    }
}

@Preview
@Composable
fun MainMenuScreenPreview() {
    LegionaryAppTheme {
        WelcomeScreen()
    }
}