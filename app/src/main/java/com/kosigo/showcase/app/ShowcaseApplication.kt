package com.kosigo.showcase.app

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.play.core.splitcompat.SplitCompatApplication
import com.kosigo.showcase.BuildConfig
import com.kosigo.showcase.app.feature.FeatureManager
import com.kosigo.showcase.app.kodein.FragmentArgsExternalSource
import com.kosigo.showcase.appModule
import com.kosigo.showcase.library.base.baseModule
import com.kosigo.showcase.library.settings.about.settingsModule
import com.kosigo.showcase.library.restaurants.restaurantsModule
import com.kosigo.showcase.library.bestsellers.bestsellersModule
import com.kosigo.showcase.library.reviews.reviewsModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import timber.log.Timber


class ShowcaseApplication : SplitCompatApplication(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ShowcaseApplication))
        import(baseModule)
        import(appModule)
        import(settingsModule)
        import(restaurantsModule)
        import(bestsellersModule)
        import(reviewsModule)
        importAll(FeatureManager.kodeinModules)

        externalSources.add(FragmentArgsExternalSource())
    }

    private val preferencesName = "night_mode_preferences"
    private val nightMode = "night_mode"

    private lateinit var context: Context

    override fun onCreate() {
        super.onCreate()

        context = this
        context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).apply {
            getInt(nightMode, AppCompatDelegate.MODE_NIGHT_YES).apply {
                AppCompatDelegate.setDefaultNightMode(this)
            }
        }
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
