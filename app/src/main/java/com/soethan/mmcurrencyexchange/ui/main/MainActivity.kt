package com.soethan.mmcurrencyexchange.ui.main

import android.content.Context
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.soethan.mmcurrencyexchange.R
import com.soethan.mmcurrencyexchange.databinding.ActivityMainBinding
import com.soethan.mmcurrencyexchange.util.extension.hide
import com.soethan.mmcurrencyexchange.util.extension.show
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinExperimentalAPI
import timber.log.Timber

@KoinExperimentalAPI
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        mainBinding.navView.setupWithNavController(navController)
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            if (destination.id == R.id.splashScreenFragment) {
//                mainBinding.navView.hide()
//            }else{
//                mainBinding.navView.show()
//            }
//        }
        observeData()
    }


    private fun observeData() {
        viewModel.isDarkThemeEnabled().observe(this, Observer { nightModeActive ->
            val defaultMode = if (nightModeActive) {
                AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.MODE_NIGHT_NO
            }
            AppCompatDelegate.setDefaultNightMode(defaultMode)
        })
    }


}