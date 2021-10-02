package com.example.legionaryapp.components.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.legionaryapp.components.TextFieldState
import com.example.legionaryapp.R
import com.example.legionaryapp.components.tasks.TasksBody
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

@Composable
fun WelcomeBody(
    onSignInSubmitted: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .semantics { contentDescription = "SignIn Screen" },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting()
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(R.drawable.e_legion_sphere),
            contentDescription = null,
            modifier = Modifier
                .size(240.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { onSignInSubmitted() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
        ) {
            Text(
                text = "Войти"
            )
        }
    }
}

@Composable
fun Greeting() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text="Добро пожаловать", fontSize = MaterialTheme.typography.h4.fontSize, fontWeight = FontWeight.Bold)
        Text(text="в e-Legion", fontSize = MaterialTheme.typography.h4.fontSize, fontWeight = FontWeight.Bold)
    }
}


@Preview
@Composable
fun WelcomeBodyPreview() {
    LegionaryAppTheme {
        WelcomeBody(onSignInSubmitted = {

        })
    }
}