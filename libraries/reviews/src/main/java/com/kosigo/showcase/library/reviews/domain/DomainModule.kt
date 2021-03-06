package com.kosigo.showcase.library.reviews.domain

import com.kosigo.showcase.library.reviews.MODULE_NAME
import com.kosigo.showcase.library.reviews.domain.usecase.GetProductUseCase
import com.kosigo.showcase.library.reviews.domain.usecase.GetProductsListUseCase
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

internal val domainModule = Kodein.Module("${MODULE_NAME}DomainModule") {

    bind() from singleton { GetProductsListUseCase(instance()) }

    bind() from singleton { GetProductUseCase(instance()) }

}
