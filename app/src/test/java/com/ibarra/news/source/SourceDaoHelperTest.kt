package com.ibarra.news.source

import com.ibarra.news.data.remote.domain.SourceRepository

val sourceRepository = SourceRepository (
    idName = "abc-news",
    name = "ABC News",
    description = "Your trusted source for breaking news, analysis, exclusive interviews, headlines, and videos at ABCNews.com.",
    url = "https://abcnews.go.com",
    category = "general",
    language = "en",
    country = "us"
)