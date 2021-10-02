package com.example.legionaryapp.components.welcome

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.legionaryapp.components.MainScreen
import com.example.legionaryapp.components.tasks.TasksBody
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.navigation.LegionaryScreen
import com.example.legionaryapp.ui.theme.LegionaryAppTheme


@Composable
fun WelcomeScreen() {
    LegionaryAppTheme {
        val navController = rememberNavController()
        WelcomeNavHost(
            navController = navController, modifier = Modifier
        )
    }
}


@Composable
fun WelcomeNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LegionaryScreen.Welcome.name,
        modifier = modifier
    ) {
        composable(LegionaryScreen.Welcome.name) {
            WelcomeBody(
                onSignInSubmitted = { navController.navigate(LegionaryScreen.Main.name) }
            )
        }
        composable(LegionaryScreen.Main.name) {
             MainScreen()
        }
    }
}


@Preview
@Composable
fun MainMenuScreenPreview() {
    LegionaryAppTheme {
        WelcomeScreen()
    }
}