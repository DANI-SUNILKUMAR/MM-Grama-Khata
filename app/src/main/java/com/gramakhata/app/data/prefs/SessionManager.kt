package com.gramakhata.app.data.prefs

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("GramaKhataSession", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_USER_ROLE = "user_role"
    }

    fun saveSession(userId: Long, username: String, role: String) {
        prefs.edit().apply {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putLong(KEY_USER_ID, userId)
            putString(KEY_USERNAME, username)
            putString(KEY_USER_ROLE, role)
            apply()
        }
    }

    fun isLoggedIn(): Boolean = prefs.getBoolean(KEY_IS_LOGGED_IN, false)

    fun getUserId(): Long = prefs.getLong(KEY_USER_ID, -1L)

    fun getUsername(): String? = prefs.getString(KEY_USERNAME, null)

    fun getUserRole(): String = prefs.getString(KEY_USER_ROLE, "USER") ?: "USER"

    fun isAdmin(): Boolean = getUserRole() == "ADMIN"

    fun logout() {
        prefs.edit().clear().apply()
    }
}
