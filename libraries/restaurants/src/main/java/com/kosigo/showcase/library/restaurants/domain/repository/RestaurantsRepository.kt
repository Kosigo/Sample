package com.kosigo.showcase.library.restaurants.domain.repository

import com.kosigo.showcase.library.restaurants.data.model.State
import com.kosigo.showcase.library.restaurants.domain.model.RestaurantDomainModel
import kotlinx.coroutines.flow.Flow


interface RestaurantsRepository {

    suspend fun getRestaurantInfo(artistName: String, albumName: String, mbId: String?): RestaurantDomainModel?

    suspend fun getRestaurantsFromServer(): List<RestaurantDomainModel>

    suspend fun getRestaurantsFlow(): Flow<State<List<RestaurantDomainModel>>>

    suspend fun getSearchRestaurantsFlow(textSearch:String): Flow<State<List<RestaurantDomainModel>>>

}
