package com.ibarra.news.data.remote.domain

import com.google.gson.annotations.SerializedName

class SourcesResponse (
    @SerializedName("status") val status: String,
    @SerializedName("sources") val sources: List<SourceRepository>?
)