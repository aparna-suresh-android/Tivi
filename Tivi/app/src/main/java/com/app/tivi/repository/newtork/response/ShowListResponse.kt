package com.app.tivi.repository.newtork.response

/**
 * "page":1,
"results":[
{
"backdrop_path":"/o7qi2v4uWQ8bZ1tW3KI0Ztn2epk.jpg",
"first_air_date":"2019-11-12",
"genre_ids":[
10765,
10759
],
"id":82856,
"name":"The Mandalorian",
"origin_country":[
"US"
],
"original_language":"en",
"original_name":"The Mandalorian",
"overview":"After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
"popularity":1383.972,
"poster_path":"/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
"vote_average":8.5,
"vote_count":4844
}],
"total_pages":500,
"total_results":10000
 */

 class ShowListResponse (
    val page : Int,
    val results: Array<ShowItemResponse>,
    val totalPages: Int,
    val totalResults : Int
){

}