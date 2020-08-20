package com.kosigo.showcase.library.settings.about

import com.kosigo.showcase.library.settings.about.data.dataModule
import com.kosigo.showcase.library.settings.about.domain.domainModule
import com.kosigo.showcase.library.settings.about.presentation.presentationModule
import org.kodein.di.Kodein


internal const val MODULE_NAME = "Settings"


val settingsModule = Kodein.Module("${MODULE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}
