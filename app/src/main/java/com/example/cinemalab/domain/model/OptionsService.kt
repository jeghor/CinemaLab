package com.example.cinemalab.domain.model

typealias OptionListener = (option: List<Option>) -> Unit

class OptionsService {

    private var option = mutableListOf<Option>()
    var genres = mutableListOf<Option>()
    var countries = mutableListOf<Option>()

    private val listeners = mutableSetOf<OptionListener>()

    fun getOptionList(): List<Option> = option
    fun setOptionList(list: List<Option>) {
        option.addAll(option)
        notifyChanges()
    }

    fun getItemCount(): Int = option.size

    fun getSelected(): List<Option> {
        val selectedOptions = mutableListOf<Option>()
        option.forEach {
            if (it.isSelected) {
                selectedOptions.add(it)
            }
        }
        return selectedOptions
    }

    fun selectOption(option: Option) {
        option.isSelected = true
    }

    fun clearOptions() {
        option.forEach {
            if (it.isSelected) {
                it.isSelected = false
            }
        }
    }

    fun addListener(listener: OptionListener) {
        listeners.add(listener)
        listener.invoke(option)
    }

    fun removeListener(listener: OptionListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(option) }
    }
}