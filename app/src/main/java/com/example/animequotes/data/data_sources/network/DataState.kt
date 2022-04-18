package com.example.animequotes.data.data_sources.network

//TODO CHANGE TO SEALED CLASS
class DataState<out T>(val status: Status, val data: T?, val msg: String?) {

    /*
    class Success<T>(data: T): DataState<T>(data)
    class Error<T>(msg: String, data: T? = null): DataState<T>(data, msg)
    class Loading<T>(): DataState<T>(null)
     */

    companion object {
        fun <T> success(data: T?): DataState<T> {
            return DataState(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String): DataState<T> {
            return DataState(Status.ERROR, null, msg)
        }

        fun <T> loading(): DataState<T> {
            return DataState(Status.LOADING, null, null)
        }

        fun <T> default(): DataState<T> {
            return DataState(Status.DEFAULT, null, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    DEFAULT
}