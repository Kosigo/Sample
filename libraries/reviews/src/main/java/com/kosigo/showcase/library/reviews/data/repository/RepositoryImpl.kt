package com.kosigo.showcase.library.reviews.data.repository

import com.kosigo.showcase.library.reviews.data.model.ReviewsDataModelDao
import com.kosigo.showcase.library.reviews.data.model.State
import com.kosigo.showcase.library.reviews.data.model.toDomainModel
import com.kosigo.showcase.library.reviews.data.retrofit.service.RetrofitService
import com.kosigo.showcase.library.reviews.domain.model.ReviewsDomainModel
import com.kosigo.showcase.library.reviews.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

internal class RepositoryImpl(
    private val retrofitService: RetrofitService,
    private val reviewsDataModelDao: ReviewsDataModelDao
) : Repository {

    override suspend fun getReviewsInfo(name: String) = flow<State<ReviewsDomainModel>> {
        reviewsDataModelDao.getItemFlow(name)
            .map { item -> item.toDomainModel() }
            .catch { it.message?.let { message -> emit(State.failed(message)) } }
            .flowOn(Dispatchers.IO)
            .collect { emit(State.success(it)) }
    }

    override suspend fun getReviewsFromServer() =
        retrofitService.searchAlbumAsync()
            .apply {
                reviewsDataModelDao.deleteAll()
                reviewsDataModelDao.insertList(this)
            }
            .map { it.toDomainModel() }


    override suspend fun getReviewsFlow() = flow<State<List<ReviewsDomainModel>>> {
        reviewsDataModelDao.getListFlow()
            .map { items ->
                items.asFlow()
                    .onEach { Timber.d("::reviews::$it") }
                    .map { item -> item.toDomainModel() }
                    .toList()
            }
            .catch { it.message?.let { message -> emit(State.failed(message)) } }
            .flowOn(Dispatchers.IO)
            .collect {
                if (it.isEmpty()) {
                    emit(State.success(getReviewsFromServer()))
                } else {
                    emit(State.success(it))
                }
            }
    }


}
