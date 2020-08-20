package com.kosigo.showcase.library.bestsellers.domain.model

import com.chad.library.adapter.base.entity.MultiItemEntity


data class ProductDomainModel(
   val name: String,
   val logo: String,
   val price: Double,
   val description: String

) : MultiItemEntity {
    override fun getItemType(): Int {
        return 0
    }
}
