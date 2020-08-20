package com.kosigo.showcase.library.reviews.domain.repository

import com.kosigo.showcase.library.reviews.data.model.State
import com.kosigo.showcase.library.reviews.domain.model.ReviewsDomainModel
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun getReviewsInfo(name: String): Flow<State<ReviewsDomainModel>>

    suspend fun getReviewsFromServer(): List<ReviewsDomainModel>

    suspend fun getReviewsFlow(): Flow<State<List<ReviewsDomainModel>>>

}
