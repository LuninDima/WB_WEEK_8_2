package com.example.wb_week_8_2.view.listHeroes

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.wb_week_8_2.view.listHeroes.ListHeroesFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object ListHeroesScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment =
        ListHeroesFragment()
}