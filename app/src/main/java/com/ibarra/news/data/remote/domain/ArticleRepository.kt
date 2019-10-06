package com.ibarra.news.data.remote.domain

import com.google.gson.annotations.SerializedName
import java.util.*

class ArticleRepository (
    @SerializedName("source") val source: ArticleSourceRepository,
    @SerializedName("author") val author: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String,
    @SerializedName("urlToImage") val urlToImage: String?,
    @SerializedName("publishedAt") val publishedAt: Date?,
    @SerializedName("content") val content: String?
)