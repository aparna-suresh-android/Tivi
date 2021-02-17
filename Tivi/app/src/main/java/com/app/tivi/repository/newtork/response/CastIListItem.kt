package com.app.tivi.repository.newtork.response

import com.app.tivi.repository.newtork.Network
import kotlin.math.roundToInt

/**
 *  ""adult": false,
"gender": 1,
"id": 934289,
"known_for_department": "Acting",
"name": "Kiernan Shipka",
"original_name": "Kiernan Shipka",
"popularity": 7.596,
"profile_path": "/aZeL5x1QfJ9LVpXfpTzyQilJTjN.jpg",
"roles": [
{
"credit_id": "5aeb0dbec3a3682dfe005af3",
"character": "Sabrina Spellman / Sabrina Morningstar",
"episode_count": 36
}
],
"total_episode_count": 36,
"order": 0
 */
data class CastIListItem(
    val adult : Boolean,
    val name : String,
    val totalEpisodeCount : Int,
    val profilePath : String,
    val roles : Array<CharacterPlayed>
) : IListItemResponse{
    data class CharacterPlayed(
        val creditId : String,
        val character : String,
        val episodeCount : Int
    )

    override val posterUrl : String
        get() = Network.IMAGE_POSTER_URL + profilePath;
    override val backdropUrl : String
        get() = Network.IMAGE_POSTER_URL + profilePath;
    override val score : Int
        get() = 0
}