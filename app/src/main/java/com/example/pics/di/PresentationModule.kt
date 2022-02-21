package com.example.pics.di

import com.example.pics.ui.main.pics.PicsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { PicsViewModel(application = androidApplication(), repository = get()) }
}
