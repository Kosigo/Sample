package com.kosigo.showcase.library.bestsellers.data.model

import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import com.squareup.moshi.Json

@androidx.room.Entity(primaryKeys = ["name"])
data class ProductDataModel(
    @field:Json(name = "ProductName") val name: String,
    @field:Json(name = "ProductImage") val logo: String,
    @field:Json(name = "ProductPrice") val price: Double,
    @field:Json(name = "ProductDescription") val description: String
)

internal fun ProductDataModel.toDomainModel(): ProductDomainModel {
    return ProductDomainModel(
        name = this.name,
        logo = this.logo,
        price = this.price,
        description = this.description
    )
}
