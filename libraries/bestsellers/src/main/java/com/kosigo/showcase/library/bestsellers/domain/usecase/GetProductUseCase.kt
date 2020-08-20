package com.kosigo.showcase.library.bestsellers.domain.usecase

import com.kosigo.showcase.library.bestsellers.domain.repository.Repository

class GetProductUseCase(
    private val repository: Repository
) {
    suspend fun execute(artistName: String) = repository.getRestaurantInfo(artistName)
}
