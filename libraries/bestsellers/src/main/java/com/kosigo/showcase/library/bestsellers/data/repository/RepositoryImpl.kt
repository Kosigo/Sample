package com.kosigo.showcase.library.bestsellers.data.repository

import com.kosigo.showcase.library.bestsellers.data.model.ProductDataModelDao
import com.kosigo.showcase.library.bestsellers.data.model.State
import com.kosigo.showcase.library.bestsellers.data.model.toDomainModel
import com.kosigo.showcase.library.bestsellers.data.retrofit.service.RetrofitService
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import com.kosigo.showcase.library.bestsellers.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

internal class RepositoryImpl(
    private val retrofitService: RetrofitService,
    private val productDataModelDao: ProductDataModelDao
) : Repository {

    override suspend fun getRestaurantInfo(name: String) = flow<State<ProductDomainModel>> {
        productDataModelDao.getItemFlow(name)
            .map { item -> item.toDomainModel() }
            .catch { it.message?.let { message -> emit(State.failed(message)) } }
            .flowOn(Dispatchers.IO)
            .collect { emit(State.success(it)) }
    }

    override suspend fun getRestaurantsFromServer() =
        retrofitService.searchAlbumAsync()
            .apply {
                productDataModelDao.deleteAll()
                productDataModelDao.insertList(this)
            }
            .map { it.toDomainModel() }


    override suspend fun getRestaurantsFlow() = flow<State<List<ProductDomainModel>>> {
        productDataModelDao.getListFlow()
            .map { items ->
                items.asFlow()
                    .onEach { Timber.d("::product::$it") }
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

    override suspend fun getSearchRestaurantsFlow(textSearch: String) = flow<State<List<ProductDomainModel>>> {
        flowOf(productDataModelDao.getList())
            .map { items ->
                items.asFlow()
                    .filter { it.name.contains(textSearch, true) }
                    .map { item -> item.toDomainModel() }
                    .toList()
            }
            .catch {
                it.message?.let { message -> emit(State.failed(message)) }
            }
            .flowOn(Dispatchers.IO)
            .collect {
                if (it.isEmpty()) emit(State.failed("Ничего не найдено"))
                emit(State.success(it))
            }
    }
}
