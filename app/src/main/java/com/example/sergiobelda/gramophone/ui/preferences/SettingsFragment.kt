package com.example.sergiobelda.gramophone.ui.preferences


import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

import com.example.sergiobelda.gramophone.R

/**
 *
 *
 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_main, rootKey)
    }
}
