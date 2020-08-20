package com.kosigo.showcase.library.bestsellers.domain.repository

import com.kosigo.showcase.library.bestsellers.data.model.State
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import kotlinx.coroutines.flow.Flow


interface Repository {

    suspend fun getRestaurantInfo(name: String): Flow<State<ProductDomainModel>>

    suspend fun getRestaurantsFromServer(): List<ProductDomainModel>

    suspend fun getRestaurantsFlow(): Flow<State<List<ProductDomainModel>>>

    suspend fun getSearchRestaurantsFlow(textSearch:String): Flow<State<List<ProductDomainModel>>>

}
