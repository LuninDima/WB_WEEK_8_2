package com.example.wb_week_8_2.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_8_2.App
import com.example.wb_week_8_2.R
import com.example.wb_week_8_2.databinding.ActivityMainBinding
import com.example.wb_week_8_2.view.about.AboutScreen
import com.example.wb_week_8_2.view.listHeroes.ListHeroesScreen
import com.example.wb_week_8_2.viewModel.ListHeroViewModel
import com.example.wb_week_8_2.viewModel.MainViewModelFactory
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var model: ListHeroViewModel
    private lateinit var binding: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.fragment_container)
    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var router: Router
    @Inject lateinit var listHeroesScreen: ListHeroesScreen
    @Inject lateinit var aboutScreen: AboutScreen


    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       App.appComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        model = ViewModelProvider(this, viewModelFactory).get(ListHeroViewModel::class.java)
        if (savedInstanceState == null) {
            router.newRootScreen(ListHeroesScreen)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_screen_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuAbout -> {
                router.newChain(aboutScreen)
                true
            }
            R.id.menuMain -> {
                router.newChain(listHeroesScreen)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }


    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()

    }
}