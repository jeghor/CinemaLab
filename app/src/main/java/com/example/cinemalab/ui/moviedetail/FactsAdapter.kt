package com.example.cinemalab.ui.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalab.data.remote.model.movie.Fact
import com.example.cinemalab.databinding.ItemFactBinding

class FactsAdapter: RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    var factsList = listOf<Fact>()

    inner class FactsViewHolder(
        val binding: ItemFactBinding
    ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemFactBinding.inflate(layoutInflater, parent, false)
        return FactsViewHolder(binding)
    }

    override fun getItemCount(): Int = factsList.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val fact = factsList[position]
        with(holder.binding){
            factText.text = fact.value
            if (fact.spoiler){
                spoiler.visibility = View.VISIBLE
            }
        }
    }

}