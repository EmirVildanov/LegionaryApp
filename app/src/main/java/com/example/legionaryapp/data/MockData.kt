package com.example.legionaryapp.data

import com.example.legionaryapp.network.Category
import com.example.legionaryapp.network.Task

val mockCategories = listOf(
    Category(
        1,
        "Офис",
        "Список курсов, включающих в себя задачи по адаптации к работе в офисе и формированию комфортного рабочего пространства"
    ),
    Category(
        2,
        "Технологии",
        "Курс по изучению технологий e-Legion, внутренних процессов"
    ),
    Category(
        3,
        "Социализация",
        "Курс по изучению технологий e-Legion, внутренних процессов"
    ),
    Category(
        4,
        "Фидбеки",
        "Фидбеки по пройденной адаптации, предложения по улучшению адаптационного периода для будущих сотрудников"
    )
)

val mockTasks = listOf(
    Task(
        id = 0,
        title = "Sign papers",
        description = "Description",
        category = Category(
            1,
            "Офис",
            "Список курсов, включающих в себя задачи по адаптации к работе в офисе и формированию комфортного рабочего пространства"
        ),
        isComplete = true,
        isImportant = true
    ),
    Task(
        id = 1,
        title = "Get to know everybody",
        description = "Description",
        category = Category(
            2,
            "Технологии",
            "Курс по изучению технологий e-Legion, внутренних процессов"
        ),
        isComplete = true,
        isImportant = true
    ),
    Task(
        id = 2,
        title = "Get access to secret info",
        description = "Secret",
        category = Category(
            4,
            "Фидбеки",
            "Фидбеки по пройденной адаптации, предложения по улучшению адаптационного периода для будущих сотрудников"
        ),
        isComplete = true,
        isImportant = true
    )
)