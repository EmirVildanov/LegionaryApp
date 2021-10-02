package com.example.legionaryapp.network

val production = false

const val PROD_SERVER_URL = "heroku"
const val LOCAL_SERVER_URL = "http://192.168.0.104:8080"

val SERVER_URL = if (production) PROD_SERVER_URL else LOCAL_SERVER_URL

// GET

val ME_ENDPOINT = "${SERVER_URL}/me"
val MY_TASKS_ENDPOINT = "${SERVER_URL}/myTasks"


// POST

val TASK_STATUS_ENDPOINT = "${SERVER_URL}/taskStatus"
