package com.app.tivi.utils

object Converters {

    @JvmStatic
    fun convertStringArrayToString(array : Array<String>?,separator : Char) : String{
        var text = ""
        array?.let{
            text = array[0];
            for(i in 1 until array.size){
                text = "${text}${separator} ${array[i]}"
            }
        }
        return text;
    }

    @JvmStatic
    fun convertMinutesToHour(minutes : Int) : String{
        if(minutes < 60){
            return "${Integer.toString(minutes)}minutes"
        }else{
            var hour = minutes/60;
            var remaining = minutes % 60;
            if(remaining > 0){
                return "${hour}hour ${remaining}minutes"
            }else{
                return "${hour}hour"
            }
        }
    }
}