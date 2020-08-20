package com.kosigo.showcase.library.settings.about.presentation.about


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButtonToggleGroup
import com.kosigo.showcase.library.settings.R
import com.kosigo.showcase.library.base.presentation.fragment.BaseContainerFragment
import com.kosigo.showcase.library.settings.BuildConfig
import kotlinx.android.synthetic.main.about_app.*
import kotlinx.android.synthetic.main.about_settings.*
import org.kodein.di.generic.instance


class AboutFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_about

    private val viewModel: AboutViewModel by instance<AboutViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_app_version.text = String.format(getString(R.string.text_version), BuildConfig.VERSION_NAME)
        click_feedback.setOnClickListener { viewModel.onClickFeedback() }

        theme_toggle_group.check(viewModel.themePreferencesManager.currentThemeId)
        theme_toggle_group.addOnButtonCheckedListener { _: MaterialButtonToggleGroup?, checkedId: Int, isChecked: Boolean ->
            if (isChecked) viewModel.themePreferencesManager.saveAndApplyTheme(checkedId)
        }

        viewModel.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.detachView()
    }

    fun feedbackApp() {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("kosigo.com@gmail.com"))
            putExtra(
                Intent.EXTRA_SUBJECT,
                String.format(getString(R.string.text_feedback_app), BuildConfig.VERSION_NAME)
            )
        }
        if (intent.resolveActivity(requireContext().packageManager) != null) startActivity(intent)
    }

}
