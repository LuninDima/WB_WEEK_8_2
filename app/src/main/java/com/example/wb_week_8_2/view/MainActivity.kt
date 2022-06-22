package com.example.wb_week_8_2.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.wb_week_8_2.App
import com.example.wb_week_8_2.R
import com.example.wb_week_8_2.databinding.ActivityMainBinding
import com.example.wb_week_8_2.view.about.AboutScreen
import com.example.wb_week_8_2.view.listHeroes.ListHeroesScreen
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.fragment_container)
    private val router = App.appInstants?.router

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.appInstants?.navigatorHolder?.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            router?.newRootScreen(ListHeroesScreen)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAbout -> {
                router?.newChain(AboutScreen)
                true
            }
            R.id.menuMain -> {
                router?.newChain(ListHeroesScreen)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    override fun onPause() {
        App.appInstants?.navigatorHolder?.removeNavigator()
        super.onPause()

    }
}