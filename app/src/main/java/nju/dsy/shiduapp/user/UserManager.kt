package nju.dsy.shiduapp.user
// UserManager.kt
import android.content.Context
import android.content.SharedPreferences

object UserManager {
    private const val PREF_NAME = "user_pref"
    private const val KEY_USERNAME = "username"
    private const val KEY_PASSWORD = "password"

    fun login(context: Context, username: String, password: String): Boolean {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedUsername = preferences.getString(KEY_USERNAME, null)
        val savedPassword = preferences.getString(KEY_PASSWORD, null)

        if (savedUsername == username && savedPassword == password) {
            return true // 登录成功
        }

        return false // 登录失败
    }

    fun register(context: Context, username: String, password: String): Boolean {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        // 检查用户名是否已存在
        val savedUsername = preferences.getString(KEY_USERNAME, null)
        if (savedUsername == username) {
            return false // 用户名已存在，注册失败
        }

        // 保存用户名和密码
        editor.putString(KEY_USERNAME, username)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()

        return true // 注册成功
    }

    fun logout(context: Context) {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = preferences.edit()

        // 清除用户名和密码
        editor.remove(KEY_USERNAME)
        editor.remove(KEY_PASSWORD)
        editor.apply()
    }

    fun isLoggedIn(context: Context): Boolean {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val savedUsername = preferences.getString(KEY_USERNAME, null)
        return savedUsername != null // 如果用户名存在，则认为用户已登录
    }

    fun getUsername(context: Context): String? {
        val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return preferences.getString(KEY_USERNAME, null)
    }
}

