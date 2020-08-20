package com.kosigo.showcase.library.restaurants.data

import androidx.room.Room
import com.kosigo.showcase.library.restaurants.MODULE_NAME
import com.kosigo.showcase.library.restaurants.data.model.*
import com.kosigo.showcase.library.restaurants.data.repository.RestaurantsRepositoryImpl
import com.kosigo.showcase.library.restaurants.data.retrofit.service.RetrofitService
import com.kosigo.showcase.library.restaurants.domain.repository.RestaurantsRepository
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

internal val dataModule = Kodein.Module("${MODULE_NAME}DataModule") {

    bind() from singleton {
        Room.databaseBuilder(
            instance(),
            ShopsDatabase::class.java, ShopsDatabase.DATABASE_NAME
        ).build()
    }

    bind<StringKeyValueDao>() with provider { instance<ShopsDatabase>().keyValueDao() }
    bind<RestaurantDataModelDao>() with provider { instance<ShopsDatabase>().restaurantDataModelDao() }

    bind<RestaurantsRepository>() with singleton { RestaurantsRepositoryImpl(instance(), instance()) }
    bind() from singleton { instance<Retrofit>().create(RetrofitService::class.java) }
}
