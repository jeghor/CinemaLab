package com.example.cinemalab.ui.filter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalab.databinding.ItemOptionBinding
import com.example.cinemalab.model.Country

interface CountryActionListener{
    fun onCountry(country: Country)
}

class CountriesAdapter(
    private val actionListener: CountryActionListener
): RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>(), View.OnClickListener{

    var countriesList: List<Country> = emptyList()
        set(newValue){
            field = newValue
            notifyItemInserted(countriesList.size)
        }

    inner class CountriesViewHolder(
        val binding: ItemOptionBinding
    ):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOptionBinding.inflate(inflater,parent,false)
        binding.listItem.setOnClickListener(this)
        return CountriesViewHolder(binding)
    }

    override fun getItemCount(): Int = countriesList.size

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        val country = countriesList[position]

        with(holder.binding){
            listItem.tag = country
            nameValue.text = country.country
            if (country.isSelected){
                selectIcon.visibility = View.VISIBLE
            }
        }
    }

    override fun onClick(v: View) {
        val country = v.tag as Country
        actionListener.onCountry(country)
    }

}