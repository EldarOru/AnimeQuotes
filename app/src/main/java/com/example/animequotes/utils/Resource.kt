package com.example.animequotes.utils

//TODO CHANGE TO SEALED CLASS
class Resource<out T>(val status: Status, val data: T?, val msg: String?) {

    /*
    class Success<T>(data: T): DataState<T>(data)
    class Error<T>(msg: String, data: T? = null): DataState<T>(data, msg)
    class Loading<T>(): DataState<T>(null)
     */

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): Resource<T> {
            return Resource(Status.ERROR, null, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }

        fun <T> default(): Resource<T> {
            return Resource(Status.DEFAULT, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    DEFAULT
}