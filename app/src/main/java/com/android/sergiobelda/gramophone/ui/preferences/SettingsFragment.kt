package com.android.sergiobelda.gramophone.ui.preferences


import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

import com.android.sergiobelda.gramophone.R

/**
 *
 *
 */
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_main, rootKey)
    }
}
