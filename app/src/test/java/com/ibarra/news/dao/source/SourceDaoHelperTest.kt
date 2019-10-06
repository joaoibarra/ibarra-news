package com.ibarra.news.dao.source

import com.ibarra.news.data.remote.domain.SourceRepository

val sourceRepository = SourceRepository (
    idName = "bbc-news",
    name = "BBC News",
    description = "Use BBC News for up-to-the-minute news, breaking news, video, audio and feature stories. BBC News provides trusted World and UK news as well as local and regional perspectives. Also entertainment, business, science, technology and health news.",
    url = "http://www.bbc.co.uk/news",
    category = "general",
    language = "en",
    country = "gb"
)