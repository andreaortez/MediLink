package com.example.medilink

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREF_NAME = "PulsePrefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_USER_NAME = "user_name"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserSession(context: Context, userId: String, userName: String) {
        val prefs = getPrefs(context)
        prefs.edit()
            .putString(KEY_USER_ID, userId)
            .putString(KEY_USER_NAME, userName)
            .apply()
    }

    fun getUserId(context: Context): String? {
        return getPrefs(context).getString(KEY_USER_ID, null)
    }

    fun getUserName(context: Context): String? {
        return getPrefs(context).getString(KEY_USER_NAME, null)
    }

    fun clearSession(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
