package com.kosigo.showcase.library.reviews

import com.kosigo.showcase.library.reviews.data.dataModule
import com.kosigo.showcase.library.reviews.domain.domainModule
import com.kosigo.showcase.library.reviews.presentation.presentationModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Reviews"


@ExperimentalCoroutinesApi
val reviewsModule = Kodein.Module("${MODULE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}

