package com.app.tivi.features.uiModel

import com.app.tivi.repository.newtork.Network
import com.app.tivi.repository.newtork.response.CastItemResponse

class CastListItem (response : CastItemResponse) : IListItem {

    val name  = response.name;
    val characterPlayed = response.roles[0].character
    val totalEpisodeCount = response.totalEpisodeCount;

    override val posterUrl : String  = Network.IMAGE_POSTER_URL + response.profilePath;
    override val backdropUrl : String = Network.IMAGE_POSTER_URL + response.profilePath;

}