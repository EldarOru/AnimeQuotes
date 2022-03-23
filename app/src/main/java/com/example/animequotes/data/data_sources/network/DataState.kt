package com.example.animequotes.data.data_sources.network

class DataState<out T>(val status: Status, val data: T?, val msg: String?) {
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
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING,
    DEFAULT
}