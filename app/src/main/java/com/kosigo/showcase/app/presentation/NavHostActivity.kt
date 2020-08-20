package com.kosigo.showcase.app.presentation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kosigo.showcase.R
import com.kosigo.showcase.library.base.presentation.activity.BaseActivity
import com.kosigo.showcase.library.base.presentation.navigation.NavManager
import kotlinx.android.synthetic.main.activity_nav_host.*
import org.kodein.di.generic.instance

class NavHostActivity : BaseActivity() {

    override val layoutResId = R.layout.activity_nav_host

    private val navController get() = navHostFragment.findNavController()

    private val navManager: NavManager by instance<NavManager>()

    private var currentNavController: LiveData<NavController>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initNavManager()
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState!!)
        setupBottomNavigationBar()
    }

    private fun initNavManager() {
        navManager.setOnNavEvent {
            navController.navigate(it)
        }
    }

    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        val navGraphIds =
            listOf(
                R.navigation.restaurants_graph,
                R.navigation.bestsellers_graph,
                R.navigation.reviews_graph,
                R.navigation.settings_graph
            )

        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.navHostFragment,
            intent = intent
        )

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }
}
