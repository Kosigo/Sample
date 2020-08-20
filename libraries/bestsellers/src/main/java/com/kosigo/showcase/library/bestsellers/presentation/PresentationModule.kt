package com.kosigo.showcase.library.bestsellers.presentation

import androidx.fragment.app.Fragment
import com.kosigo.showcase.library.bestsellers.MODULE_NAME
import com.kosigo.showcase.library.bestsellers.presentation.item.ProductItemViewModel
import com.kosigo.showcase.library.bestsellers.presentation.list.BestsellersViewModel
import com.kosigo.showcase.library.base.di.KotlinViewModelProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.kodein.di.Kodein
import org.kodein.di.android.x.AndroidLifecycleScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

@FlowPreview
@ExperimentalCoroutinesApi
internal val presentationModule = Kodein.Module("${MODULE_NAME}PresentationModule") {

    bind<BestsellersViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { BestsellersViewModel(instance()) }
    }

    bind<ProductItemViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { ProductItemViewModel(instance()) }
    }

}
