package com.app.tivi.features.uiModel

import com.app.tivi.repository.newtork.Network
import com.app.tivi.repository.newtork.response.ShowDetailsResponse
import java.util.*
import kotlin.math.roundToInt

class ShowDetails(response : ShowDetailsResponse) :  IListItem {
    val id : Long = response.id;
    val name : String = response.name;
    val episodeRunTime = response.episodeRunTime[0];
    val overview = response.overview;
    val tagline = response.tagline;
    var createdBy : String = "";
    private var firstAirDate : Date = response.firstAirDate;
    var  firstAirYear : Int = 0
    lateinit var genreNames : Array<String>


    override val posterUrl : String  = Network.IMAGE_POSTER_URL + response.posterPath;
    override val backdropUrl : String = Network.IMAGE_POSTER_URL + response.backdropPath;
    override val score : Int = ((response.voteAverage / 10) * 100).roundToInt();

    init{
        response.createdBy?.let{
            if(it.isNotEmpty()){
                createdBy = it.get(0)?.name
            }
        }

        initFirstAirYearAndGenere(response)
    }

    private fun initFirstAirYearAndGenere(response : ShowDetailsResponse){
        val calendar = Calendar.getInstance()
        calendar.time = firstAirDate
        firstAirYear = calendar.get(Calendar.YEAR);


        var array  = Array(response.genres.size){
            ""
        };
        for((i, genre) in response.genres.withIndex()){
            array[i] = genre.name;
        }
        genreNames = array;
    }


}
