package com.example.pics

import android.app.Application
import com.example.pics.data.network.ApiProvider
import com.example.pics.di.networkModule
import com.example.pics.di.presentationModule
import com.example.pics.di.repositoriesModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
        initServiceProvider()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initServiceProvider() {
        (get<ApiProvider>()).init()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    networkModule,
                    repositoriesModule,
                    presentationModule
                )
            )
        }
    }
}
