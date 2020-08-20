package com.kosigo.showcase.library.restaurants.presentation.list

import android.widget.ImageView
import coil.api.load
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kosigo.showcase.library.restaurants.R
import com.kosigo.showcase.library.restaurants.domain.model.RestaurantDomainModel

class RestaurantsAdapter : BaseMultiItemQuickAdapter<RestaurantDomainModel, BaseViewHolder>(arrayListOf()) {

    init {
        addItemType(0, R.layout.adapter_list_item_2)
    }

    override fun convert(helper: BaseViewHolder, item: RestaurantDomainModel) {
        helper.setText(R.id.tv_title, item.name)
        helper.setText(R.id.tv_sub_title, "Время доставки " + item.deliveryTime + " мин")
        helper.itemView.findViewById<ImageView>(R.id.main_image).load(item.logo)
    }
}
