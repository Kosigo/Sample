package com.kosigo.showcase.library.reviews.data.model

import com.kosigo.showcase.library.reviews.domain.model.ReviewsDomainModel
import com.squareup.moshi.Json

@androidx.room.Entity(primaryKeys = ["message"])
data class ReviewsDataModel(
    @field:Json(name = "Message") val message: String,
    @field:Json(name = "IsPositive") val isPositive: Boolean,
    @field:Json(name = "DateAdded") val dateAdded: String,
    @field:Json(name = "UserFIO") val userFIO: String,
    @field:Json(name = "RestaurantName") val restaurantName: String
)

internal fun ReviewsDataModel.toDomainModel(): ReviewsDomainModel {
    return ReviewsDomainModel(
        message = this.message,
        isPositive = this.isPositive,
        dateAdded = this.dateAdded,
        userFIO = this.userFIO,
        restaurantName = this.restaurantName
    )
}
