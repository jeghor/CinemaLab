package com.example.cinemalab.data.remote.mapper

import com.example.cinemalab.data.remote.model.PossibleValuesByField
import com.example.cinemalab.domain.model.Option
import com.example.cinemalab.domain.model.OptionsList
import retrofit2.Response

class OptionMapper: EntityMapper<Response<PossibleValuesByField>,OptionsList> {
    override fun mapFromModel(model: Response<PossibleValuesByField>): OptionsList {
        val option = model.body()
        val optionsList = OptionsList()
        option?.forEach {
            val opt = Option(name = it.name)
            optionsList.add(opt)
        }
        return optionsList
    }
}
