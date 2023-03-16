package com.example.cinemalab.ui.filter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalab.databinding.ItemOptionBinding
import com.example.cinemalab.model.Genre

interface GenreActionListener{
    fun onGenre(genre: Genre)
}

class GenresAdapter(
    private val actionListener: GenreActionListener
): RecyclerView.Adapter<GenresAdapter.GenresViewHolder>(), View.OnClickListener{

    var genresList: List<Genre> = emptyList()
        set(newValue){
            field = newValue
            notifyItemInserted(genresList.size)
        }

    inner class GenresViewHolder(
        val binding: ItemOptionBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenresViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOptionBinding.inflate(inflater,parent,false)

        binding.listItem.setOnClickListener(this)

        return GenresViewHolder(binding)
    }

    override fun getItemCount(): Int = genresList.size

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre = genresList[position]

        with(holder.binding){
            listItem.tag = genre
            nameValue.text = genre.genre
            if (genre.isSelected){
                selectIcon.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(v: View) {
        val genre = v.tag as Genre
        actionListener.onGenre(genre)
    }
}