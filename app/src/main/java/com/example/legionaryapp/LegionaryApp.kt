package com.example.legionaryapp

import android.app.Application
import timber.log.Timber

class LegionaryApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}