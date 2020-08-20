package com.kosigo.showcase.library.restaurants.domain.model

import com.chad.library.adapter.base.entity.MultiItemEntity


data class RestaurantDomainModel(
    val name: String,
    val logo: String,
    val minCost: String,
    val deliveryCost: String,
    val deliveryTime: String,
    val positiveReviews: String,
    val reviewsCount: String
) : MultiItemEntity {
    override fun getItemType(): Int {
        return 0
    }
}
