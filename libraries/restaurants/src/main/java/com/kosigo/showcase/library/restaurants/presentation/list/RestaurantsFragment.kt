package com.kosigo.showcase.library.restaurants.presentation.list

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialSharedAxis
import com.kosigo.showcase.library.restaurants.data.model.State
import com.kosigo.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.generic.instance
import com.kosigo.showcase.library.restaurants.R
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect

@FlowPreview
@ExperimentalCoroutinesApi
class RestaurantsFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_list

    private val viewModel: RestaurantsViewModel by instance()
    private lateinit var adapter: RestaurantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transition = MaterialSharedAxis.create(MaterialSharedAxis.Y, true)
        transition.addTarget(RecyclerView::class.java)
        TransitionManager.beginDelayedTransition(catalog_list, transition)

        adapter = RestaurantsAdapter()
        catalog_list.layoutManager = GridLayoutManager(context, 1)
        catalog_list.adapter = adapter

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
                        adapter.setNewData(it.data)
                    }
                    is State.Failed -> {
                        progress_bar.hide()
                        showToast("Failed! ${it.message}")
                    }
                }
            }
        }

        et_search.addTextChangedListener {
            viewModel.onSearch(it.toString())
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
