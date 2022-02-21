package com.example.pics.data.network

import com.example.pics.BuildConfig
import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit.*

class ApiProvider(
    private val interceptors: List<Interceptor> = emptyList(),
    private val gson: Gson
) {
    private lateinit var api: PicsApi

    companion object {
        private const val CALL_TIMEOUT = 60L
        private const val CONNECTION_TIMEOUT = 30L
        private const val READ_TIMEOUT = 30L
        private const val WRITE_TIMEOUT = 30L
    }

    fun getServiceApi(): PicsApi = api

    fun init() {
        api = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .apply {
                        connectTimeout(CONNECTION_TIMEOUT, SECONDS)
                        readTimeout(READ_TIMEOUT, SECONDS)
                        writeTimeout(WRITE_TIMEOUT, SECONDS)
                        callTimeout(CALL_TIMEOUT, SECONDS)
                        interceptors.forEach { addInterceptor(it) }
                    }
                    .build()
            )
            .build()
            .create(PicsApi::class.java)
    }
}
