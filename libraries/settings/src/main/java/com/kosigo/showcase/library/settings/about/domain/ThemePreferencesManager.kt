package com.kosigo.showcase.library.settings.about.domain

import android.content.Context
import android.content.SharedPreferences
import android.util.SparseIntArray
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatDelegate
import com.kosigo.showcase.library.settings.R

class ThemePreferencesManager(private val context: Context) {

    companion object {
        private const val PREFERENCES_NAME = "night_mode_preferences"
        private const val KEY_NIGHT_MODE = "night_mode"
        private val THEME_NIGHT_MODE_MAP = SparseIntArray()

        init {
            THEME_NIGHT_MODE_MAP.append(R.id.theme_light, AppCompatDelegate.MODE_NIGHT_NO)
            THEME_NIGHT_MODE_MAP.append(R.id.theme_dark, AppCompatDelegate.MODE_NIGHT_YES)
            THEME_NIGHT_MODE_MAP.append(R.id.theme_default, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    fun saveAndApplyTheme(@IdRes id: Int) {
        val nightMode = convertToNightMode(id)
        saveNightMode(nightMode)
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }

    @get:IdRes
    val currentThemeId: Int
        get() = convertToThemeId(nightMode)

    val themeIds: IntArray
        get() {
            val themeIds = IntArray(THEME_NIGHT_MODE_MAP.size())
            for (i in 0 until THEME_NIGHT_MODE_MAP.size()) {
                themeIds[i] = THEME_NIGHT_MODE_MAP.keyAt(i)
            }
            return themeIds
        }

    private val nightMode: Int
        get() = sharedPreferences.getInt(KEY_NIGHT_MODE, AppCompatDelegate.MODE_NIGHT_YES)

    private fun saveNightMode(nightMode: Int) {
        sharedPreferences.edit().putInt(KEY_NIGHT_MODE, nightMode).apply()
    }

    private val sharedPreferences: SharedPreferences
        get() = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    private fun convertToNightMode(@IdRes id: Int): Int {
        return THEME_NIGHT_MODE_MAP[id, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM]
    }

    @IdRes
    private fun convertToThemeId(nightMode: Int): Int {
        return THEME_NIGHT_MODE_MAP.keyAt(
            THEME_NIGHT_MODE_MAP.indexOfValue(nightMode))
    }

}
