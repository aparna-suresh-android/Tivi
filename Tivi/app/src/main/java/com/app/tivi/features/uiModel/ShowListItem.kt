package com.app.tivi.features.uiModel

import com.app.tivi.repository.database.entity.FavouriteShow
import com.app.tivi.repository.newtork.Network
import com.app.tivi.repository.newtork.response.ShowItemResponse
import kotlin.math.roundToInt

class ShowListItem () : IListItem {

    var posterPath: String = "";
    var id : Long = 0;
    var name : String = "";
    var isFavourite : Boolean = false;
    var voteAverage : Float = 0.0F;
    override val posterUrl : String
        get() = Network.IMAGE_POSTER_URL + posterPath;
    override val score : Int
        get() = ((voteAverage / 10) * 100).roundToInt();

    constructor(response: ShowItemResponse) : this() {
        id  = response.id;
        this.name = response.name;
        voteAverage = response.voteAverage;
        posterPath = response.posterPath

    }

    constructor(dbResult: FavouriteShow) : this() {
        id  = dbResult.id;
        name = dbResult.name;
        voteAverage = dbResult.voteAverage;
        posterPath = dbResult.posterUrl
        isFavourite = true
    }

}