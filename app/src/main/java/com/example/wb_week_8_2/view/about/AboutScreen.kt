package com.example.wb_week_8_2.view.about

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AboutScreen: FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return AboutFragment()
    }
}