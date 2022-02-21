package com.example.pics.data.repositories

import com.example.pics.data.network.PicsApi
import com.example.pics.data.network.Request
import com.example.pics.data.network.getOutput
import com.example.pics.data.network.mapper.PicsMapper
import com.example.pics.domain.model.Pic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class PicsRepository(private val api: PicsApi) {
    suspend fun getPics(limit: Int = 100): Request<List<Pic>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getPics(limit)
                getOutput(response, PicsMapper)
            } catch (ex: Exception) {
                Timber.d("getPics request error: %s", ex.message)
                Request.Error.ConnectionIssue
            }
        }
    }
}
