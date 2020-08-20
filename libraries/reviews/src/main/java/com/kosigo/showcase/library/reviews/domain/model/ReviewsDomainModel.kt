package com.kosigo.showcase.library.reviews.domain.model

import com.chad.library.adapter.base.entity.MultiItemEntity


data class ReviewsDomainModel(
   val message: String,
   val isPositive: Boolean,
   val dateAdded: String,
   val userFIO: String,
   val restaurantName: String

) : MultiItemEntity {
    override fun getItemType(): Int {
        return 0
    }
}
