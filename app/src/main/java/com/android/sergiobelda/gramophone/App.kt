/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        val modeNight = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("mode_night", false)
        if (modeNight) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}