package com.example.wb_week_8_2.view.listHeroes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.wb_week_8_2.App
import com.example.wb_week_8_2.viewModel.AppStateList
import com.example.wb_week_8_2.R
import com.example.wb_week_8_2.databinding.FragmentListHeroesBinding
import com.example.wb_week_8_2.model.Hero
import com.example.wb_week_8_2.utils.showSnackBar
import com.example.wb_week_8_2.view.heroDetails.HeroDetailsFragment
import com.example.wb_week_8_2.view.heroDetails.HeroDetailsScreen
import com.example.wb_week_8_2.viewModel.ListHeroViewModel

class ListHeroesFragment : Fragment() {

    private var _binding: FragmentListHeroesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ListHeroViewModel by lazy { ViewModelProvider(this).get(ListHeroViewModel::class.java) }
    private val adapter = ListHeroesFragmentAdapter(object : OnItemViewClickListener {

        override fun onItemViewClick(hero: Hero) {
            openHeroDetailsFragment(hero)
        }
    })

    private fun openHeroDetailsFragment(hero: Hero) {
        val bundle = Bundle().apply {
            putParcelable(HeroDetailsFragment.BUNDLE_EXTRA, hero)
        }
        App.appInstants?.router?.navigateTo(HeroDetailsScreen(bundle))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel.getDataHeroes()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListHeroesBinding.inflate(inflater, container, false)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        binding.listHeroesFragmentRecyclerView.adapter = adapter
        return binding.root
    }

    private fun renderData(appStateList: AppStateList) {
        with(binding) {
            with(listHeroesFragmentLoadingLayout) {
                when (appStateList) {
                    is AppStateList.Success -> {
                        listHeroesFragmentLoadingLayout.visibility = View.GONE
                        adapter.setListHeroes(appStateList.Hero)
                    }
                    is AppStateList.Loading -> {
                        listHeroesFragmentLoadingLayout.visibility = View.VISIBLE
                    }
                    is AppStateList.Error -> {
                        listHeroesFragmentLoadingLayout.visibility = View.GONE
                        listHeroesFragmentRootView.showSnackBar(
                            getString(R.string.error),
                            getString(R.string.reload),
                            {
                                viewModel.getListHeroFromRemoteServer()
                            }
                        )

                    }
                }
            }
        }
    }

    interface OnItemViewClickListener {
        fun onItemViewClick(Hero: Hero)
    }

    override fun onDestroy() {
        _binding = null
        adapter.removeListener()
        super.onDestroy()
    }
}