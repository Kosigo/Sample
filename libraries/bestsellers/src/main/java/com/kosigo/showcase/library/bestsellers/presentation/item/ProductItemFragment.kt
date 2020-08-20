package com.kosigo.showcase.library.bestsellers.presentation.item

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.google.android.material.transition.MaterialContainerTransform
import com.kosigo.showcase.library.bestsellers.presentation.Constants.ITEM_ID
import com.kosigo.showcase.library.bestsellers.presentation.Constants.ITEM_NAME
import com.kosigo.showcase.library.base.presentation.fragment.BaseContainerFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.kodein.di.generic.instance
import com.kosigo.showcase.library.bestsellers.R
import com.kosigo.showcase.library.bestsellers.data.model.State
import kotlinx.android.synthetic.main.fragment_product_info.*
import kotlinx.coroutines.flow.collect


@ExperimentalCoroutinesApi
class ProductItemFragment : BaseContainerFragment() {

    override val layoutResourceId: Int = R.layout.fragment_product_info

    private val viewModel: ProductItemViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpTransitions()
        viewModel.setIdProduct(arguments?.getString(ITEM_ID) ?: "")
    }

    private fun setUpTransitions() {
        val transform = MaterialContainerTransform()
        sharedElementEnterTransition = transform
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val container = view.findViewById<View>(R.id.container_fragment)
        ViewCompat.setTransitionName(container, arguments?.getString(ITEM_NAME))

        lifecycleScope.launchWhenCreated {
            viewModel.itemData.collect {
                when (it) {
                    is State.Loading -> {
                    }
                    is State.Success -> {
                        main_image.load(it.data.logo)
                        tv_title.text = it.data.name
                        tv_price.text = "${it.data.price} " + getString(R.string.currency)
                        tv_description.text = it.data.description
                    }
                    is State.Failed -> {
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
