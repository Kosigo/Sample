package com.kosigo.showcase.library.bestsellers.domain.usecase

import com.kosigo.showcase.library.bestsellers.data.model.State
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import com.kosigo.showcase.library.bestsellers.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetProductsListUseCase(
    private val repository: Repository
) {

    suspend fun execute(): Flow<State<List<ProductDomainModel>>> {
        return repository.getRestaurantsFlow()
    }

    suspend fun executeSearch(textSearch: String): Flow<State<List<ProductDomainModel>>> {
        return repository.getSearchRestaurantsFlow(textSearch)
    }
}
