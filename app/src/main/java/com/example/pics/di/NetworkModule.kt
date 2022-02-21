package com.example.pics.di

import com.example.pics.data.network.ApiProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {
    single<Gson> { GsonBuilder().create() }
    single {
        HttpLoggingInterceptor().apply {
            level = (HttpLoggingInterceptor.Level.BODY)
        }
    }
    single {
        ApiProvider(
            gson = get(),
            interceptors = listOf(get<HttpLoggingInterceptor>())
        )
    }
    single { get<ApiProvider>().getServiceApi() }
}
