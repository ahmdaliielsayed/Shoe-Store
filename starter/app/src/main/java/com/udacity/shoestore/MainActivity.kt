package com.udacity.shoestore

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import com.udacity.shoestore.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var _dataBinder: ActivityMainBinding? = null
    private val dataBinder: ActivityMainBinding
        get() = _dataBinder!!

    lateinit var navController: NavController

    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataBinder = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        this._dataBinder = dataBinder

        val navHostFragment = supportFragmentManager.findFragmentById(navLaunchGraph.id) as NavHostFragment
        navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            topLevelDestinationIds = setOf(
                R.id.homeFragment,
            )
        )
    }

    override fun onDestroy() {
        _dataBinder?.unbind()
        _dataBinder = null
        super.onDestroy()
    }
}
