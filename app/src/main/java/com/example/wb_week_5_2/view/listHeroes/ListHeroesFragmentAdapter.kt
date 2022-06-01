package com.example.wb_week_5_2.view.listHeroes

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_5_2.R
import com.example.wb_week_5_2.model.Hero
import com.squareup.picasso.Picasso

class ListHeroesFragmentAdapter(
    private var onItemViewClickListener:
    ListHeroesFragment.OnItemViewClickListener?
) :
    RecyclerView.Adapter<ListHeroesFragmentAdapter.MainViewHolder>() {

    private var listHeroesData: List<Hero> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setListHeroes(data: List<Hero>) {
        listHeroesData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_list_heroes_recycler_item, parent, false)
                    as View
        )
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listHeroesData[position])
    }

    override fun getItemCount(): Int {
        return listHeroesData.size
    }

    fun removeListener() {
        onItemViewClickListener = null
    }

    inner class MainViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(hero: Hero) {
            itemView.apply {
                val heroImage = findViewById<ImageView>(R.id.ivListHeroesFragmentRecyclerItem)

                Picasso.with(context)
                    .load(hero.image.url)
                    .into(heroImage);

                findViewById<TextView>(R.id.tvListHeroesFragmentRecyclerItem).text =
                    hero.name
                setOnClickListener {
                    onItemViewClickListener?.onItemViewClick(hero)
                }
            }
        }
    }
}
