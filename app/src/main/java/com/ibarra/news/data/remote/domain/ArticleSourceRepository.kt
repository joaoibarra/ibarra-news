package com.ibarra.news.data.remote.domain

import com.google.gson.annotations.SerializedName

class ArticleSourceRepository (
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String?
)