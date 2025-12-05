package com.example.medilink

import android.content.Context
import android.content.SharedPreferences

object SessionManager {

    private const val PREF_NAME = "PulsePrefs"

    private const val KEY_USER_ID = "user_id"
    private const val KEY_USER_NAME = "user_name"
    private const val KEY_USER_LASTNAME = "user_lastname"
    private const val KEY_USER_TYPE = "user_type"
    private const val KEY_USER_EMAIL = "user_email"

    private const val KEY_PHONE = "user_phone"
    private const val KEY_AGE = "user_age"

    private fun getPrefs(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun saveUserSession(
        context: Context,
        userId: String,
        userName: String,
        userLastName: String,
        userType: String,
        userEmail: String,
        phone: String? = null,
        age: Int? = null
    ) {
        val editor = getPrefs(context).edit()
            .putString(KEY_USER_ID, userId)
            .putString(KEY_USER_NAME, userName)
            .putString(KEY_USER_LASTNAME, userLastName)
            .putString(KEY_USER_TYPE, userType)
            .putString(KEY_USER_EMAIL, userEmail)

        if (phone != null) editor.putString(KEY_PHONE, phone)
        if (age != null) editor.putInt(KEY_AGE, age)

        editor.apply()
    }

    fun getUserId(context: Context): String? =
        getPrefs(context).getString(KEY_USER_ID, null)

    fun getUserName(context: Context): String? =
        getPrefs(context).getString(KEY_USER_NAME, null)

    fun getUserLastName(context: Context): String? =
        getPrefs(context).getString(KEY_USER_LASTNAME, null)

    fun getUserType(context: Context): String? =
        getPrefs(context).getString(KEY_USER_TYPE, null)

    fun getUserEmail(context: Context): String? =
        getPrefs(context).getString(KEY_USER_EMAIL, null)

    fun getUserPhone(context: Context): String? =
        getPrefs(context).getString(KEY_PHONE, null)

    fun getUserAge(context: Context): Int? {
        val prefs = getPrefs(context)
        return if (prefs.contains(KEY_AGE)) prefs.getInt(KEY_AGE, 0) else null
    }

    fun setUserName(context: Context, userName: String) {
        getPrefs(context).edit().putString(KEY_USER_NAME, userName).apply()
    }

    fun setUserLastName(context: Context, userLastName: String) {
        getPrefs(context).edit().putString(KEY_USER_LASTNAME, userLastName).apply()
    }

    fun setUserType(context: Context, userType: String) {
        getPrefs(context).edit().putString(KEY_USER_TYPE, userType).apply()
    }

    fun setUserEmail(context: Context, userEmail: String) {
        getPrefs(context).edit().putString(KEY_USER_EMAIL, userEmail).apply()
    }

    fun setUserPhone(context: Context, phone: String?) {
        getPrefs(context).edit().putString(KEY_PHONE, phone).apply()
    }

    fun setUserAge(context: Context, age: Int?) {
        val editor = getPrefs(context).edit()
        if (age == null) {
            editor.remove(KEY_AGE)
        } else {
            editor.putInt(KEY_AGE, age)
        }
        editor.apply()
    }

    fun clearSession(context: Context) {
        getPrefs(context).edit().clear().apply()
    }
}
