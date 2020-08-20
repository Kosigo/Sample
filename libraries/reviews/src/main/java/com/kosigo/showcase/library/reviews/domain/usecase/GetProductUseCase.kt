package com.kosigo.showcase.library.reviews.domain.usecase

import com.kosigo.showcase.library.reviews.domain.repository.Repository

class GetProductUseCase(
    private val repository: Repository
) {
    suspend fun execute(artistName: String) = repository.getReviewsInfo(artistName)
}
