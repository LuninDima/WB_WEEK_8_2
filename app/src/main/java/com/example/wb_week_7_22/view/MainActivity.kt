package com.example.wb_week_7_22.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.wb_week_7_22.R
import com.example.wb_week_7_22.databinding.ActivityMainBinding
import com.example.wb_week_7_22.view.listHeroes.ListHeroesFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ListHeroesFragment.newInstance())
                .commitAllowingStateLoss()
        }


    }
}