package com.example.cinemalab.model

typealias FactsListener = (facts: List<Fact>) -> Unit

class FactsService {

    private var facts = mutableListOf<Fact>(
        Fact(1,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
        Fact(3,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
        Fact(2,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
        Fact(4,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
        Fact(4,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
        Fact(4,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
        Fact(4,"https://kinopoiskapiunofficial.tech/images/posters/kp_small/301.jpg","Для показа Нео, утыканного иглами, возникла необходимость обратиться к практикующему иглоукалывателю. Иглы в кадре настоящие, хотя значительная их часть воткнута не в актера, а в макет. В голову актера иглы втыкать пришлось по-настоящему."),
    )

    private val listeners = mutableSetOf<FactsListener>()

    fun getFacts(): List<Fact> = facts

    fun addListener(listener: FactsListener){
        listeners.add(listener)
        listener.invoke(facts)
    }

    fun removeListener(listener: FactsListener){
        listeners.remove(listener)
    }
}