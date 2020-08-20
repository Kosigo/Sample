package com.kosigo.showcase.library.restaurants.data.model

import com.kosigo.showcase.library.restaurants.domain.model.RestaurantDomainModel
import com.squareup.moshi.Json

@androidx.room.Entity(primaryKeys = ["name"])
data class RestaurantDataModel(
    @field:Json(name = "Name") val name: String,
    @field:Json(name = "Logo") val logo: String,
    @field:Json(name = "MinCost") val minCost: String,
    @field:Json(name = "DeliveryCost") val deliveryCost: String,
    @field:Json(name = "DeliveryTime") val deliveryTime: String,
    @field:Json(name = "PositiveReviews") val positiveReviews: String,
    @field:Json(name = "ReviewsCount") val reviewsCount: String

)

internal fun RestaurantDataModel.toDomainModel(): RestaurantDomainModel {

    return RestaurantDomainModel(
        name = this.name,
        logo = this.logo,
        minCost = this.minCost,
        deliveryCost = this.deliveryCost,
        deliveryTime = this.deliveryTime,
        positiveReviews = this.positiveReviews,
        reviewsCount = this.reviewsCount
    )
}
