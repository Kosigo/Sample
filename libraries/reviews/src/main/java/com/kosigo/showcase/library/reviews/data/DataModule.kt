package com.kosigo.showcase.library.reviews.data

import androidx.room.Room
import com.kosigo.showcase.library.reviews.MODULE_NAME
import com.kosigo.showcase.library.reviews.data.model.*
import com.kosigo.showcase.library.reviews.data.repository.RepositoryImpl
import com.kosigo.showcase.library.reviews.data.retrofit.service.RetrofitService
import com.kosigo.showcase.library.reviews.domain.repository.Repository
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
            Database::class.java, Database.DATABASE_NAME
        ).build()
    }

    bind<StringKeyValueDao>() with provider { instance<Database>().keyValueDao() }
    bind<ReviewsDataModelDao>() with provider { instance<Database>().restaurantDataModelDao() }

    bind<Repository>() with singleton { RepositoryImpl(instance(), instance()) }
    bind() from singleton { instance<Retrofit>().create(RetrofitService::class.java) }
}
