package com.example.legionaryapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.example.legionaryapp.components.guide.GuideBody
import com.example.legionaryapp.components.news.NewsBody
import com.example.legionaryapp.components.tasks.SingleTask
import com.example.legionaryapp.components.tasks.TasksBody
import com.example.legionaryapp.components.welcome.WelcomeBody
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.navigation.LegionaryScreen
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

@Composable
fun MainScreen() {
    LegionaryAppTheme {
        val allScreens = listOf(LegionaryScreen.News, LegionaryScreen.Tasks, LegionaryScreen.Guide)
        val navController = rememberNavController()
        val backstackEntry = navController.currentBackStackEntryAsState()
        val currentScreen = LegionaryScreen.fromRoute(
            backstackEntry.value?.destination?.route
        )
        Scaffold(
            bottomBar = {
                LegionaryTabRow(
                    allScreens = allScreens,
                    onTabSelected = { screen -> navController.navigate(screen.name) },
                    currentScreen = currentScreen
                )
            }
        ) { innerPadding ->
            LegionaryNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun LegionaryNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = LegionaryScreen.Tasks.name,
        modifier = modifier
    ) {
        composable(LegionaryScreen.Tasks.name) {
            TasksBody()
        }
        composable(LegionaryScreen.News.name) {
            NewsBody()
        }
        composable(LegionaryScreen.Guide.name) {
            GuideBody()
        }

        val tasksName = LegionaryScreen.Tasks.name
        val tasksNameArgument = "name"
        composable(
            route = "$tasksName/{$tasksNameArgument}",
            arguments = listOf(
                navArgument(tasksNameArgument) {
                    type = NavType.StringType
                }
            ),
            deepLinks =  listOf(navDeepLink {
                uriPattern = "rally://$tasksName/{name}"
            })
        ) { entry ->
            val taskName = entry.arguments?.getString(tasksNameArgument)
            val task = UserRepository.getTask(taskName)
            SingleTask(task = task)
        }
    }
}

private fun navigateToSingleTask(
    navController: NavHostController,
    taskName: String
) {
    navController.navigate("${LegionaryScreen.Tasks.name}/$taskName")
}