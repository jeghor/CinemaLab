package com.example.cinemalab.ui.filter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemalab.databinding.ItemOptionBinding
import com.example.cinemalab.domain.model.Option

interface OptionActionListener {
    fun onOption(option: Option)
}

class OptionAdapter(
    private val actionListener: OptionActionListener
) : RecyclerView.Adapter<OptionAdapter.OptionViewHolder>(), View.OnClickListener {

    var optionList: List<Option> = emptyList()
        set(newValue) {
            field = newValue
            notifyItemInserted(optionList.size)
        }

    inner class OptionViewHolder(
        val binding: ItemOptionBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemOptionBinding.inflate(inflater, parent, false)

        binding.listItem.setOnClickListener(this)

        return OptionViewHolder(binding)
    }

    override fun getItemCount(): Int = optionList.size

    override fun onBindViewHolder(holder: OptionViewHolder, position: Int) {
        val option = optionList[position]

        with(holder.binding) {
            listItem.tag = option
            nameValue.text = option.name
            if (option.isSelected) {
                selectIcon.visibility = View.VISIBLE
            } else selectIcon.visibility = View.GONE
        }
    }

    override fun onClick(v: View) {
        val option = v.tag as Option
        actionListener.onOption(option)
    }
}