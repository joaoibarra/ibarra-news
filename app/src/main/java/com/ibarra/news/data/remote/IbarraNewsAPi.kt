package com.ibarra.news.data.remote

import retrofit2.http.GET

interface IbarraNewsAPi {

    @GET("v2/sources")
    fun getSources()

    @GET("v2/top-headlines")
    fun getTopHeadLines()
}