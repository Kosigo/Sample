package com.kosigo.showcase.library.reviews.domain.usecase

import com.kosigo.showcase.library.reviews.data.model.State
import com.kosigo.showcase.library.reviews.domain.model.ReviewsDomainModel
import com.kosigo.showcase.library.reviews.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetProductsListUseCase(
    private val repository: Repository
) {

    suspend fun execute(): Flow<State<List<ReviewsDomainModel>>> {
        return repository.getReviewsFlow()
    }

}
