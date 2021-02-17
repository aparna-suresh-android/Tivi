package com.app.tivi.utils


open class Event<out T>(private val content: T) {

    var isClickEventHandled = false
        private set


    fun getEventDataIfNotHandled(): T? {
        return if (isClickEventHandled) {
            null
        } else {
            isClickEventHandled = true
            content
        }
    }
}