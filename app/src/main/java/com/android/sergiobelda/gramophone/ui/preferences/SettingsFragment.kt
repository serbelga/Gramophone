package com.android.sergiobelda.gramophone.ui.preferences


import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

import com.android.sergiobelda.gramophone.R

/**
 *
 *
 */
class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.pref_main, rootKey)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "theme") {
            Log.i(TAG, "Preference value was updated to: " + sharedPreferences!!.getString(key, ""))
        }
    }

    companion object {
        private const val TAG = "SettingsFragment"
    }
}
