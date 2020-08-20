package com.kosigo.showcase.library.restaurants.domain

import com.kosigo.showcase.library.restaurants.MODULE_NAME
import com.kosigo.showcase.library.restaurants.domain.usecase.GetRestaurantsListUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetRestaurantsListUseCase(instance()) }
}
