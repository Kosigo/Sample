package com.kosigo.showcase.library.bestsellers.presentation.list

import android.widget.ImageView
import androidx.core.view.ViewCompat
import coil.api.load
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kosigo.showcase.library.bestsellers.R
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel

class BestsellersAdapter :BaseMultiItemQuickAdapter<ProductDomainModel, BaseViewHolder>(arrayListOf()){

    init {
        addItemType(0, R.layout.adapter_item)
    }

    override fun convert(helper: BaseViewHolder, item: ProductDomainModel) {
        helper.setText(R.id.tv_title, item.name )
        helper.setText(R.id.tv_price, "${item.price} " +  helper.itemView.context.getString(R.string.currency))
        ViewCompat.setTransitionName(helper.itemView, item.name)
        helper.itemView.findViewById<ImageView>(R.id.main_image).load(item.logo)
    }
}
