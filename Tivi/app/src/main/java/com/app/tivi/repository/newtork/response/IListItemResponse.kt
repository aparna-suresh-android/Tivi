package com.app.tivi.repository.newtork.response

import android.util.Log
import com.app.tivi.repository.newtork.Network
import kotlin.math.roundToInt

interface IListItemResponse {
    val posterUrl : String
    val backdropUrl : String
    val score : Int
}