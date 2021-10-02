package com.example.legionaryapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.legionaryapp.components.guide.GuideBody
import com.example.legionaryapp.components.LegionaryTabRow
import com.example.legionaryapp.components.news.NewsBody
import com.example.legionaryapp.data.UserRepository
import com.example.legionaryapp.components.tasks.SingleTask
import com.example.legionaryapp.components.tasks.TasksBody
import com.example.legionaryapp.components.welcome.WelcomeBody
import com.example.legionaryapp.components.welcome.WelcomeScreen
import com.example.legionaryapp.navigation.LegionaryScreen
import com.example.legionaryapp.ui.theme.LegionaryAppTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WelcomeScreen()
        }
    }
}