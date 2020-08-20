package com.kosigo.showcase.library.bestsellers.presentation.list

import android.os.Bundle
import android.transition.TransitionManager
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.transition.Hold
import com.google.android.material.transition.MaterialSharedAxis
import com.kosigo.showcase.library.bestsellers.presentation.Constants.ITEM_ID
import com.kosigo.showcase.library.bestsellers.presentation.Constants.ITEM_NAME
import com.kosigo.showcase.library.bestsellers.data.model.State
import com.kosigo.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.android.synthetic.main.fragment_product_list.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.generic.instance
import com.kosigo.showcase.library.bestsellers.R
import com.kosigo.showcase.library.bestsellers.domain.model.ProductDomainModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect

@FlowPreview
@ExperimentalCoroutinesApi
class BestsellersFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_product_list

    private val viewModel: BestsellersViewModel by instance()
    private lateinit var bestsellersAdapter: BestsellersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = Hold()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transition = MaterialSharedAxis.create(MaterialSharedAxis.Y, true)
        transition.addTarget(RecyclerView::class.java)
        TransitionManager.beginDelayedTransition(catalog_list, transition)

        bestsellersAdapter = BestsellersAdapter()
        catalog_list.layoutManager = GridLayoutManager(context, 2)
        catalog_list.adapter = bestsellersAdapter

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
                        bestsellersAdapter.setNewData(it.data)
                    }
                    is State.Failed -> {
                        progress_bar.hide()
                        showToast("Failed! ${it.message}")
                    }
                }
            }
        }

        bestsellersAdapter.setOnItemClickListener { adapter, viewItem, position ->
            val bundle = bundleOf(
                ITEM_ID to (adapter.getItem(position) as ProductDomainModel).name,
                ITEM_NAME to ViewCompat.getTransitionName(viewItem)!!
            )
            findNavController().navigate(
                R.id.action_listFragment_to_itemFragment,
                bundle,
                null,
                FragmentNavigatorExtras(
                    viewItem to ViewCompat.getTransitionName(viewItem)!!
                )
            )
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}
