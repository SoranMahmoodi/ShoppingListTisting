package com.soran.shoppingtest.utils

open class Event<out T>(private val content: T) {
    var hasBeenHandles = false
        private set

    fun getContentIfHandled(): T? {
        return if (hasBeenHandles) {
            null
        } else {
            hasBeenHandles = true
            content
        }
    }

    fun peekContent(): T = content
}