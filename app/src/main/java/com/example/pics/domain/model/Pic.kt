package com.example.pics.domain.model

data class Pic(
    val id: String,
    val author: String,
    val downloadUrl: String,
    val height: Int,
    val width: Int
)
