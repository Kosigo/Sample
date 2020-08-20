package com.kosigo.showcase.library.restaurants.data.repository

import com.kosigo.showcase.library.restaurants.data.model.RestaurantDataModelDao
import com.kosigo.showcase.library.restaurants.data.model.State
import com.kosigo.showcase.library.restaurants.data.model.toDomainModel
import com.kosigo.showcase.library.restaurants.data.retrofit.service.RetrofitService
import com.kosigo.showcase.library.restaurants.domain.model.RestaurantDomainModel
import com.kosigo.showcase.library.restaurants.domain.repository.RestaurantsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

internal class RestaurantsRepositoryImpl(
    private val retrofitService: RetrofitService,
    private val restaurantDataModelDao: RestaurantDataModelDao
) : RestaurantsRepository {

    override suspend fun getRestaurantInfo(artistName: String, albumName: String, mbId: String?) =
        retrofitService.getAlbumInfoAsync(artistName, albumName, mbId)
            ?.restaurant
            ?.toDomainModel()

    override suspend fun getRestaurantsFromServer() =
        retrofitService.searchAlbumAsync()
            .apply {
                restaurantDataModelDao.deleteAll()
                restaurantDataModelDao.insertList(this)
            }
            .map { it.toDomainModel() }


    override suspend fun getRestaurantsFlow() = flow<State<List<RestaurantDomainModel>>> {
        restaurantDataModelDao.getListFlow()
            .map { items ->
                items.asFlow()
                    .onEach { Timber.d("::restaurant::$it") }
                    .map { item -> item.toDomainModel() }
                    .toList()
            }
            .catch { it.message?.let { message -> emit(State.failed(message)) } }
            .flowOn(Dispatchers.IO)
            .collect {
                if (it.isEmpty()) {
                    emit(State.success(getRestaurantsFromServer()))
                } else {
                    emit(State.success(it))
                }
            }
    }

    override suspend fun getSearchRestaurantsFlow(textSearch: String) = flow<State<List<RestaurantDomainModel>>> {
        flowOf(restaurantDataModelDao.getList())
            .map { items ->
                items.asFlow()
                    .filter { it.name.contains(textSearch,true) }
                    .map { item -> item.toDomainModel() }
                    .toList()
            }
            .catch {
                it.message?.let { message -> emit(State.failed(message)) }
            }
            .flowOn(Dispatchers.IO)
            .collect {
                emit(State.success(it))
            }
    }
}
