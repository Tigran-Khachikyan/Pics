package com.example.pics.di

import com.example.pics.data.repositories.PicsRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { PicsRepository(api = get()) }
}
