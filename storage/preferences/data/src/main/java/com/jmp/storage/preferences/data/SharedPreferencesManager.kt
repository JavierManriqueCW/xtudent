package com.jmp.storage.preferences.data

import android.content.SharedPreferences
import javax.inject.Inject

open class SharedPreferencesManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    open fun setPreferenceBoolean(key: String, value: Boolean) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    open fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}
