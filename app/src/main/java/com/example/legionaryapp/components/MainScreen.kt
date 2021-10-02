package com.example.legionaryapp.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.legionaryapp.components.guide.GuideBody
import com.example.legionaryapp.components.news.NewsBody
import com.example.legionaryapp.components.tasks.TaskCategory
import com.example.legionaryapp.components.tasks.TasksBody
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.data.categories
import com.example.legionaryapp.navigation.LegionaryScreen
import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.Task
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

@Composable
fun MainScreen() {
    LegionaryAppTheme {
        val myTasks by remember { mutableStateOf(UserRepository.myTasks) }

        val allScreens = listOf(LegionaryScreen.News, LegionaryScreen.Tasks, LegionaryScreen.Guide)
        val navController = rememberNavController()
        val selectedTab = remember { mutableStateOf(LegionaryScreen.Tasks) }

        Scaffold(
            bottomBar = {
                LegionaryTabRow(
                    tabScreens = allScreens,
                    onTabSelected = { screen ->
                        navController.navigate(screen.name)
                        selectedTab.value = screen
                    },
                    selectedTab = selectedTab.value
                )
            }
        ) { innerPadding ->
            LegionaryNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding),
                myTasks = myTasks
            )
        }
    }
}

@Composable
fun LegionaryNavHost(
    navController: NavHostController,
    modifier: Modifier,
    myTasks: MutableState<List<Task>>
) {
    val selectedCategory = remember {
        mutableStateOf(
            myTasks.value.firstOrNull()?.category ?: Category(
                id = -1,
                name = "EMPTY",
                description = ""
            )
        )
    }

    NavHost(
        navController = navController,
        startDestination = LegionaryScreen.Tasks.name,
        modifier = modifier
    ) {
        composable(LegionaryScreen.Tasks.name) {
            TasksBody(myTasks, selectedCategory) { categoryId ->
                navigateToTaskCategory(navController, categoryId)
            }
        }
        composable(LegionaryScreen.News.name) {
            NewsBody()
        }
        composable(LegionaryScreen.Guide.name) {
            GuideBody()
        }

        val tasksName = LegionaryScreen.Tasks.name
        val categoryNameArgument = "id"
        composable(
            route = "$tasksName/{$categoryNameArgument}",
            arguments = listOf(
                navArgument(categoryNameArgument) {
                    type = NavType.IntType
                }
            ),
        ) { entry ->
            val category = entry.arguments?.getInt(categoryNameArgument)
                ?.let { categoryId -> myTasks.value.categories().find { it.id == categoryId } }
            if (category != null) {
                TaskCategory(category = category)
            }
        }
    }
}

private fun navigateToTaskCategory(
    navController: NavHostController,
    categoryId: Int
) {
    navController.navigate("${LegionaryScreen.Tasks.name}/$categoryId")
}