package com.kosigo.showcase.library.settings.about.domain

import com.kosigo.showcase.library.settings.about.MODULE_NAME
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton {
        ThemePreferencesManager(
            instance()
        )
    }

}
