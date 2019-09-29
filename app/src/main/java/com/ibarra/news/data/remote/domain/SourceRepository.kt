package com.ibarra.news.data.remote.domain

import com.google.gson.annotations.SerializedName

class SourceRepository (
    @SerializedName("id") val idName: String,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("language") val language: String?,
    @SerializedName("country") val country: String?
)