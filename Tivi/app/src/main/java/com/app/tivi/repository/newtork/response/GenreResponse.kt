package com.app.tivi.repository.newtork.response

/**
 * {
"genres": [
{
"id": 10759,
"name": "Action & Adventure"
},
{
"id": 16,
"name": "Animation"
}
 ]
 }
 */

data class GenreResponse(val genres : Array<Genre>)