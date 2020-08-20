package com.kosigo.showcase.library.restaurants

import com.kosigo.showcase.library.restaurants.data.dataModule
import com.kosigo.showcase.library.restaurants.domain.domainModule
import com.kosigo.showcase.library.restaurants.presentation.presentationModule
import org.kodein.di.Kodein

internal const val MODULE_NAME = "Restaurants"


val restaurantsModule = Kodein.Module("${MODULE_NAME}Module") {
    import(presentationModule)
    import(domainModule)
    import(dataModule)
}

