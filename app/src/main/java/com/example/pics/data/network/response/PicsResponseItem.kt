package com.example.pics.data.network.response

import com.google.gson.annotations.SerializedName

data class PicsResponseItem(
    @SerializedName("id") val id: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("download_url") val downloadUrl: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("width") val width: Int?
)
