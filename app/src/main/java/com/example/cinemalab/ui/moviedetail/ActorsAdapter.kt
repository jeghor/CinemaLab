package com.example.cinemalab.ui.moviedetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cinemalab.data.remote.model.movie.Person
import com.example.cinemalab.databinding.ItemActorBinding

class ActorsAdapter : RecyclerView.Adapter<ActorsAdapter.ActorsViewHolder>() {

    var actorsList = mutableListOf<Person>()
        set(value) {
            field = value
            notifyItemInserted(actorsList.size)
        }

    inner class ActorsViewHolder(
        val binding: ItemActorBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemActorBinding.inflate(layoutInflater, parent, false)
        return ActorsViewHolder(binding)
    }

    override fun getItemCount(): Int = actorsList.size

    override fun onBindViewHolder(holder: ActorsViewHolder, position: Int) {
        val actor = actorsList[position]

        with(holder.binding) {
            Glide.with(posterImage)
                .load(actor.photo)
                .into(posterImage)

            name.text = actor.name
        }


    }

}