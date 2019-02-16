package com.example.sergiobelda.gramophone.ui.preferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sergiobelda.gramophone.R

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings, SettingsFragment())
            .commit()
    }
}
