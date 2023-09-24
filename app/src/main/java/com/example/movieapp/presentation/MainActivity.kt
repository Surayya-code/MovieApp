package com.example.movieapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }


    private fun setup(){
        with(binding){
            val navHostFragment= supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
            val navController=navHostFragment.navController
            bottomNavigationView.setupWithNavController(navController)

            //NavigationUI.setupWithNavController(bottomNavigationView,navController)
            navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.splashFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.welcomeFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.signInFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.signUpFragment -> binding.bottomNavigationView.visibility = View.GONE
                    R.id.inputOptionFragment -> binding.bottomNavigationView.visibility = View.GONE
                    else -> binding.bottomNavigationView.visibility = View.VISIBLE
                }
            }
        }
    }
}