package com.example.wb_week_8_2.di

import com.example.wb_week_8_2.view.about.AboutScreen
import com.example.wb_week_8_2.view.listHeroes.ListHeroesScreen
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class CiceroneModule {
    var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    fun cicerone(): Cicerone<Router> = cicerone

    @Singleton
    @Provides
    fun navigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()

    @Singleton
    @Provides
    fun router(): Router = cicerone.router

    @Singleton
    @Provides
    fun getAboutScreen(): AboutScreen {
        return AboutScreen
    }

    @Singleton
    @Provides
    fun getListheroesScreen(): ListHeroesScreen {
        return ListHeroesScreen
    }
}