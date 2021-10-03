package com.example.legionaryapp.navigation

import com.example.legionaryapp.R

enum class LegionaryScreen(
    val screenName: String = "",
    val greyIconId: Int = -1,
    val blackIconId: Int = -1
) {
    Welcome,
    Main,
    Tasks(
        greyIconId = R.drawable.task_grey_icon,
        blackIconId = R.drawable.task_black_icon,
        screenName = "Курсы"
    ),
    News(
        greyIconId = R.drawable.news_grey_icon,
        blackIconId = R.drawable.news_black_icon,
        screenName = "Новости"
    ),
    Guide(
        greyIconId = R.drawable.guide_grey_icon,
        blackIconId = R.drawable.guide_black_icon,
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