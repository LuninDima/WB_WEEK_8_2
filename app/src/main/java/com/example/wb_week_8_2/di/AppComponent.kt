package com.example.wb_week_8_2.di

import com.example.wb_week_8_2.view.MainActivity
import com.example.wb_week_8_2.view.heroDetails.HeroDetailsFragment
import com.example.wb_week_8_2.view.heroDetails.HeroDetailsScreen
import com.example.wb_week_8_2.view.listHeroes.ListHeroesFragment
import com.example.wb_week_8_2.viewModel.ListHeroViewModel
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(
    modules = [AppModule::class, CiceroneModule::class, RepositoryModule::class]
)

interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(listHeroesFragment: ListHeroesFragment)
    fun inject(detailsFragment: HeroDetailsFragment)
    fun inject(detailsScreen: HeroDetailsScreen)
    fun inject(listhHeroviewModel: ListHeroViewModel)
}