package com.ibarra.news.di

import com.ibarra.news.data.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val RoomModule = module {
    single { AppDatabase.getInstance(androidApplication().applicationContext) }
    single { get<AppDatabase>().getSourceDao() }
    single { get<AppDatabase>().getArticleDao() }
}