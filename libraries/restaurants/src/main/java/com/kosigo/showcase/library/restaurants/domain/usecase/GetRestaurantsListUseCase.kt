package com.kosigo.showcase.library.restaurants.domain.usecase

import com.kosigo.showcase.library.restaurants.data.model.State
import com.kosigo.showcase.library.restaurants.domain.model.RestaurantDomainModel
import com.kosigo.showcase.library.restaurants.domain.repository.RestaurantsRepository
import kotlinx.coroutines.flow.Flow

class GetRestaurantsListUseCase(
    private val restaurantsRepository: RestaurantsRepository
) {

    suspend fun execute(): Flow<State<List<RestaurantDomainModel>>> {
        return restaurantsRepository.getRestaurantsFlow()
    }

    suspend fun executeSearch(textSearch: String): Flow<State<List<RestaurantDomainModel>>> {
        return restaurantsRepository.getSearchRestaurantsFlow(textSearch)
    }
}
