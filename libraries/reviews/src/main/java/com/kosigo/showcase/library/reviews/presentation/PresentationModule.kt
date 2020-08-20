package com.kosigo.showcase.library.reviews.presentation

import androidx.fragment.app.Fragment
import com.kosigo.showcase.library.reviews.MODULE_NAME
import com.kosigo.showcase.library.reviews.presentation.list.ReviewsViewModel
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

    bind<ReviewsViewModel>() with scoped<Fragment>(AndroidLifecycleScope).singleton {
        KotlinViewModelProvider.of(context) { ReviewsViewModel(instance()) }
    }

}
