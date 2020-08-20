package com.kosigo.showcase.library.reviews.presentation.list

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.kosigo.showcase.library.reviews.R
import com.kosigo.showcase.library.reviews.domain.model.ReviewsDomainModel
import java.text.SimpleDateFormat
import java.util.*

class ReviewsAdapter : BaseMultiItemQuickAdapter<ReviewsDomainModel, BaseViewHolder>(arrayListOf()) {

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val sdfView = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    init {
        addItemType(0, R.layout.adapter_reviews_item)
    }

    override fun convert(helper: BaseViewHolder, item: ReviewsDomainModel) {
        helper.setText(R.id.tv_title, item.message)
        helper.setText(R.id.tv_user, item.userFIO +" о ресторане ${item.restaurantName}")
        sdf.parse(item.dateAdded)?.let {
            helper.setText(R.id.tv_sub_title, sdfView.format(it))
        }
        helper.setImageResource(
            R.id.main_image,
            if (item.isPositive) R.drawable.ic_baseline_mood else R.drawable.ic_baseline_mood_bad
        )
    }
}
