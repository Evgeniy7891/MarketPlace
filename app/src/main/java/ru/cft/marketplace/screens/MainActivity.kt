package ru.cft.marketplace.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import ru.cft.marketplace.BOTTONMENU
import ru.cft.marketplace.R
import ru.cft.marketplace.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        BOTTONMENU = binding.bottomMenu
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomMenu.setupWithNavController(navController)
        binding.bottomMenu.selectedItemId
        binding.bottomMenu.isVisible = false
        setContentView(binding.root)
    }
}