package com.example.legionaryapp.network

val production = false

const val PROD_SERVER_URL = "https://fierce-meadow-91592.herokuapp.com/api"
const val LOCAL_SERVER_URL = "http://localhost:8080"

val SERVER_URL = if (production) PROD_SERVER_URL else LOCAL_SERVER_URL

// GET

val ME_ENDPOINT = "${SERVER_URL}/me"
val MY_TASKS_ENDPOINT = "${SERVER_URL}/myTasks"


// POST

val TASK_STATUS_ENDPOINT = "${SERVER_URL}/taskStatus"
