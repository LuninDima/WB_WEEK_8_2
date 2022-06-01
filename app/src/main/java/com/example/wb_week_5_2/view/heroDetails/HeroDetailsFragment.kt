package com.example.wb_week_5_2.view.heroDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.wb_week_5_2.databinding.FragmentHeroDetailsBinding
import com.example.wb_week_5_2.model.Hero
import com.squareup.picasso.Picasso

class HeroDetailsFragment : Fragment() {
    private var _binding: FragmentHeroDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHeroDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderData()
    }

    private fun renderData() {
        arguments?.getParcelable<Hero>(BUNDLE_EXTRA)?.let { hero ->
            with(hero) {
                with(binding) {
                    Picasso.with(context).load(image.url).into(ivHero)
                    tvName.text = name
                    tvIntelligenceValue.text = powerstats.intelligence
                    tvStrengthValue.text = powerstats.strength
                    tvSpeedValue.text = powerstats.speed
                    tvDurabilityValue.text = powerstats.durability
                    tvPowerValue.text = powerstats.power
                    tvCombatValue.text = powerstats.combat

                    buttonBackToListHeroesFragment.setOnClickListener {
                        backToListHeroesFragment()
                    }
                }
            }
        }
    }

    private fun backToListHeroesFragment() {
        activity?.onBackPressed()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val BUNDLE_EXTRA = "hero"
        fun newInstance(bundle: Bundle): HeroDetailsFragment {
            val fragment = HeroDetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }
}
