package com.example.legionaryapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import com.example.legionaryapp.R

enum class LegionaryScreen(
    val screenName: String = "",
    val iconId: Int = -1,
) {
    Welcome,
    Main,
    Tasks(
        iconId = R.drawable.task_icon,
        screenName = "Курсы"
    ),
    News(
        iconId = R.drawable.news_icon,
        screenName = "Новости"
    ),
    Guide(
        iconId = R.drawable.guide_icon,
        screenName = "Информация"
    );

    companion object {
        fun fromRoute(route: String?): LegionaryScreen =
            when (route?.substringBefore("/")) {
                Tasks.name -> Tasks
                News.name -> News
                Guide.name -> Guide
                Welcome.name -> Welcome
                null -> Welcome
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}