package com.example.marvelapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.marvelapp.R
import com.example.marvelapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment

        navController = navHostFragment.navController

        binding.bottomNavMain.setupWithNavController(navController)

        /**
         * Nesse Set eu passo a nível de fragmentos os destinos iniciais
         */
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.charactersFragment, R.id.favoritesFragment, R.id.aboutFragment)
        )

        /**
         * Aqui eu configuro a Toolbar para poder refletir os eventos da bottomToolbar
         */
        binding.toolbarApp.setupWithNavController(navController, appBarConfiguration)

        /**
         * Aqui eu configuro a Toolbar para poder aparecer o botão de voltar apenas nos fragmentos internos de cada
         * opção do menu, nos fragmentos principais pois não se faz necessário
         */
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val isTopLevelDestination =
                appBarConfiguration.topLevelDestinations.contains(destination.id)
            if (!isTopLevelDestination) {
                binding.toolbarApp.setNavigationIcon(R.drawable.ic_back)
            }
        }
    }
}