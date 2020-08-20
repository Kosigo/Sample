package com.kosigo.showcase.library.bestsellers

import com.kosigo.showcase.library.bestsellers.data.dataModule
import com.kosigo.showcase.library.bestsellers.domain.domainModule
import com.kosigo.showcase.library.bestsellers.presentation.presentationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Bestsellers"


@FlowPreview
@ExperimentalCoroutinesApi
val bestsellersModule = Kodein.Module("${MODULE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}

