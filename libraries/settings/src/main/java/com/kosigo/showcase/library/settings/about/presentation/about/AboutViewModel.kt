package com.kosigo.showcase.library.settings.about.presentation.about

import androidx.lifecycle.ViewModel
import com.kosigo.showcase.library.settings.about.domain.ThemePreferencesManager


class AboutViewModel  constructor(
    val themePreferencesManager: ThemePreferencesManager
) : ViewModel() {

    private var viewState: AboutFragment? = null

    fun attachView(view: AboutFragment) {
        viewState = view
    }

    fun detachView() {
        viewState = null
    }

    fun onClickFeedback() {
        viewState?.feedbackApp()
    }

}
