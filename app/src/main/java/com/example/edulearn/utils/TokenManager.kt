package com.example.edulearn.utils

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = prefs.edit()

    companion object {
        private const val PREF_NAME = "EduLearnPrefs"
        private const val KEY_TOKEN = "token"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USER_ROLE = "user_role"
        private const val KEY_USER_NAME = "user_name"
    }

    fun saveToken(token: String) {
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }

    fun hasToken(): Boolean {
        return getToken() != null
    }

    fun clearToken() {
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_USER_ID)
        editor.remove(KEY_USER_ROLE)
        editor.remove(KEY_USER_NAME)
        editor.apply()
    }

    fun saveUserId(userId: Int) {
        editor.putInt(KEY_USER_ID, userId)
        editor.apply()
    }

    fun getUserId(): Int {
        return prefs.getInt(KEY_USER_ID, -1)
    }

    fun saveUserRole(role: String) {
        editor.putString(KEY_USER_ROLE, role)
        editor.apply()
    }

    fun getUserRole(): String {
        return prefs.getString(KEY_USER_ROLE, "STUDENT") ?: "STUDENT"
    }

    fun saveUserName(name: String) {
        editor.putString(KEY_USER_NAME, name)
        editor.apply()
    }

    fun getUserName(): String? {
        return prefs.getString(KEY_USER_NAME, null)
    }
}
