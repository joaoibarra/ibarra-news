package com.ibarra.news.di

import com.ibarra.news.ui.source.SourceNewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModule = module {
    viewModel { SourceNewsViewModel(get()) }
}