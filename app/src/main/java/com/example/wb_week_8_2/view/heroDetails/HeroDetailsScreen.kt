package com.example.wb_week_8_2.view.heroDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.wb_week_8_2.view.heroDetails.HeroDetailsFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

class HeroDetailsScreen(private val bundle: Bundle): FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
       return HeroDetailsFragment.newInstance(bundle)
    }

}