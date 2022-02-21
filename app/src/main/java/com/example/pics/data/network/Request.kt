package com.example.pics.data.network

sealed class Request<out T> {

    data class Success<T>(val data: T) : Request<T>()

    sealed class Error : Request<Nothing>() {
        abstract val message: String

        object InsufficientData : Error() {
            override val message: String = "Insufficient data in response!"
        }

        object ConnectionIssue : Error() {
            override val message: String = "Check your connection!"
        }

        data class FailedResponse(
            override val message: String,
            val code: Int? = null
        ) : Error()
    }
}
