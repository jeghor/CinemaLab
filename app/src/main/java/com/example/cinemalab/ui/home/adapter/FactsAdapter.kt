package com.example.cinemalab.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.R
import com.example.cinemalab.databinding.ItemFactBinding
import com.example.cinemalab.model.Fact

class FactsAdapter: RecyclerView.Adapter<FactsAdapter.FactsViewHolder>(){

    var facts: List<Fact> = emptyList()
        set(newValue){
            field = newValue
            notifyItemInserted(facts.size)
        }

    inner class FactsViewHolder(
        val binding: ItemFactBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemFactBinding.inflate(inflater,parent,false)
        return FactsViewHolder(binding)
    }

    override fun getItemCount(): Int = facts.size

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        val fact = facts[position]
        with(holder.binding){
            factText.text = fact.fact
            if (fact.photo.isNotBlank()){
                Glide.with(posterImage.context)
                    .load(fact.photo)
                    .into(posterImage)
            } else{
                posterImage.setImageResource(R.drawable.ic_profilepicture)
            }
        }
    }
}