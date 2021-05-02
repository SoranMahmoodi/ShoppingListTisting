package com.soran.shoppingtest.utils

class Resource<out T>(val status: Status, val data: T?, val massage: String?) {
    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(massage: String?, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, massage)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
        fun <T> empty(): Resource<T> {
            return Resource(Status.EMPTY, null, null)
        }
    }
}