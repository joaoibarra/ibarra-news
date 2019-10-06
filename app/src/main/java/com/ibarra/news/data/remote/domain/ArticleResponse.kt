package com.ibarra.news.data.remote.domain

import com.google.gson.annotations.SerializedName

class ArticleResponse (
    @SerializedName("id") var idName: String?,
    @SerializedName("totalResults") val totalResults: Int?,
    @SerializedName("articles") val articles: List<ArticleRepository>?
)