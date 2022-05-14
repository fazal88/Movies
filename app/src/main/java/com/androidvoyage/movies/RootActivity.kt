package com.androidvoyage.movies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.androidvoyage.movies.databinding.ActivityRootBinding


/**
 * Created by Fazal Shaikh on 13/5/22
 */

class RootActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.mainNavHostFragment) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.navigation_main)
        navGraph.setStartDestination(R.id.splashFragment)
        navController.graph = navGraph
    }


    fun getNavController() = navController


}