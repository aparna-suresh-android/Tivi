package com.app.tivi.repository.newtork.response

import java.util.*

/**
 * "backdrop_path":"/8AdmUPTyidDebwIuakqkSt6u1II.jpg",
"created_by":[
{
"id":1098032,
"credit_id":"5b5e27d3c3a36842240312df",
"name":"Roberto Aguirre-Sacasa",
"gender":2,
"profile_path":"/PtJSrAk7LDqaV5jExCYl10lcsN.jpg"
}
],
"episode_run_time":[
60
],
"first_air_date":"2018-10-26",
"genres":[
{
"id":9648,
"name":"Mystery"
},
{
"id":10765,
"name":"Sci-Fi & Fantasy"
},
{
"id":18,
"name":"Drama"
}
],
"homepage":"https://www.netflix.com/title/80223989",
"id":79242,
"in_production":false,
"languages":[
"en"
],
"last_air_date":"2020-12-31",
"last_episode_to_air":{
"air_date":"2020-12-31",
"episode_number":16,
"id":2556567,
"name":"Chapter Thirty-Six: At The Mountains Of Madness",
"overview":"The darkest days of the void arrive as Sabrina struggles with grief and regret. Can she summon the strength to overcome an endless cycle of destruction?",
"production_code":"",
"season_number":2,
"still_path":"/5rwsv1Qovbleyz8D4YpR66APw9F.jpg",
"vote_average":8,
"vote_count":2
},
"name":"Chilling Adventures of Sabrina",
"next_episode_to_air":null,
"networks":[
{
"name":"Netflix",
"id":213,
"logo_path":"/wwemzKWzjKYJFfCeiB57q3r4Bcm.png",
"origin_country":""
}
],
"number_of_episodes":36,
"number_of_seasons":2,
"origin_country":[
"US"
],
"original_language":"en",
"original_name":"Chilling Adventures of Sabrina",
"overview":"As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
"popularity":1523.497,
"poster_path":"/yxMpoHO0CXP5o9gB7IfsciilQS4.jpg",
"production_companies":[
{
"id":27711,
"logo_path":"/3e294jszfE6cE8TOogmj0zNd6pL.png",
"name":"Berlanti Productions",
"origin_country":"US"
},
{
"id":1957,
"logo_path":"/3T19XSr6yqaLNK8uJWFImPgRax0.png",
"name":"Warner Bros. Television",
"origin_country":"US"
}
],
"production_countries":[
{
"iso_3166_1":"CA",
"name":"Canada"
},
{
"iso_3166_1":"US",
"name":"United States of America"
}
],
"seasons":[
{
"air_date":"2018-10-26",
"episode_count":20,
"id":103271,
"name":"Season 1",
"overview":"",
"poster_path":"/ndsDUwLeHSGzUlrSz0YQzsuwT8H.jpg",
"season_number":1
},
{
"air_date":"2020-01-24",
"episode_count":16,
"id":137651,
"name":"Season 2",
"overview":"All hail the queen. Chilling Adventures of Sabrina returns for Part 3 January 24, only on Netflix.",
"poster_path":"/lMHeC5qsDD0SPAadpQt8cu7jCgK.jpg",
"season_number":2
}
],
"spoken_languages":[
{
"english_name":"English",
"iso_639_1":"en",
"name":"English"
}
],
"status":"Ended",
"tagline":"High School is such a witch.",
"type":"Scripted",
"vote_average":8.4,
"vote_count":198
 */
open class ShowDetailsResponse {

     var id: Long = 0
     var createdBy: Array<CreatedBy>? = null
    lateinit var backdropPath: String
    lateinit var episodeRunTime: Array<Int>
    lateinit var firstAirDate: Date
    lateinit var genres: Array<Genre>
    lateinit var name: String
    lateinit var overview: String
    lateinit var posterPath: String
    lateinit var tagline: String
    var voteAverage: Float = 0F
    data class CreatedBy(
        val id: Long,
        val creditId: String,
        val name: String,
        val gender: Int,
        val profilePath: String
    )


}