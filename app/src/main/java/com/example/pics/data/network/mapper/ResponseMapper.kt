package com.example.pics.data.network.mapper

interface ResponseMapper<RESPONSE, MODEL> {
    fun modelFromResponse(response: RESPONSE): MODEL
}
