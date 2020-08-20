package com.kosigo.showcase.library.reviews.presentation.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.kosigo.showcase.library.reviews.data.model.State
import com.kosigo.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_reviews_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.generic.instance
import com.kosigo.showcase.library.reviews.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect

@FlowPreview
@ExperimentalCoroutinesApi
class ReviewsFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_reviews_list

    private val viewModel: ReviewsViewModel by instance()
    private lateinit var reviewsAdapter: ReviewsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewsAdapter = ReviewsAdapter()
        catalog_list.layoutManager = GridLayoutManager(context, 1)
        catalog_list.adapter = reviewsAdapter

        catalog_list.apply {
            postponeEnterTransition()
            viewTreeObserver.addOnPreDrawListener {
                startPostponedEnterTransition()
                true
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.listData.collect {
                when (it) {
                    is State.Loading -> {
                        progress_bar.show()
                    }
                    is State.Success -> {
                        progress_bar.hide()
                        reviewsAdapter.setNewData(it.data)
                    }
                    is State.Failed -> {
                        progress_bar.hide()
                        showToast("Failed! ${it.message}")
                    }
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
