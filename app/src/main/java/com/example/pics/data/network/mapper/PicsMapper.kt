package com.example.pics.data.network.mapper

import com.example.pics.data.network.response.PicsResponse
import com.example.pics.domain.model.Pic

object PicsMapper : ResponseMapper<PicsResponse, List<Pic>?> {
    override fun modelFromResponse(response: PicsResponse): List<Pic>? {
        val result: ArrayList<Pic> = arrayListOf()
        response.forEach { item ->
            with(item) {
                if (id != null && !downloadUrl.isNullOrEmpty()) {
                    result.add(
                        Pic(
                            id = id,
                            author = author ?: "undefined",
                            downloadUrl = downloadUrl,
                            height = height ?: 0,
                            width = width ?: 0
                        )
                    )
                }
            }
        }
        return if (result.isNotEmpty()) result else null
    }
}
