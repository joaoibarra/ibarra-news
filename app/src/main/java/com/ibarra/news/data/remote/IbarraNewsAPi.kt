package com.ibarra.news.data.remote

import com.ibarra.news.BuildConfig
import com.ibarra.news.data.remote.domain.ArticleResponse
import com.ibarra.news.data.remote.domain.SourcesResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IbarraNewsAPi {

    @GET("v2/sources")
    fun getSources(@Query("apiKey") apiKey: String = BuildConfig.API_KEY): Observable<SourcesResponse>

    @GET("v2/everything")
    fun getEverything(@Query("sources") source: String,
                        @Query("page") page: Int,
                        @Query("pageSize") pageSize: Int = 20,
                        @Query("apiKey") apiKey: String = BuildConfig.API_KEY): Observable<ArticleResponse>
}