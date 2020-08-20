package com.kosigo.showcase.library.settings.about.presentation

import androidx.fragment.app.Fragment
import com.kosigo.showcase.library.settings.about.MODULE_NAME
import com.kosigo.showcase.library.settings.about.presentation.about.AboutViewModel
import com.kosigo.showcase.library.base.di.KotlinViewModelProvider
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<AboutViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) {
            AboutViewModel(
                instance()
            )
        }
    }
}
