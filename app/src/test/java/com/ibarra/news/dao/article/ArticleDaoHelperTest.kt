package com.ibarra.news.dao.article

import com.ibarra.news.data.remote.domain.ArticleRepository
import com.ibarra.news.data.remote.domain.ArticleSourceRepository
import java.util.*

val articleRepository = ArticleRepository (
    source = ArticleSourceRepository( "bbc-news","BBC News"),
    author = null,
    title = "David Luiz: Can you name players to score for Arsenal & Chelsea in Premier League?",
    description = "As David Luiz becomes the eighth player to score for Arsenal and Chelsea in the Premier League, can you name the others?",
    url = "https://www.bbc.co.uk/sport/football/49953700",
    urlToImage = "https://ichef.bbci.co.uk/onesport/cps/624/cpsprodpb/D8E2/production/_109122555_gettyimages-1173944437.jpg",
    publishedAt = Date(),
    content = "David Luiz joined Paris St-Germain for £40m in June 2014 before returning to Chelsea for £34m in August 2016\\r\\nWhen David Luiz headed Arsenal ahead against Bournemouth on Sunday, he became the latest player to score a Premier League goal for Arsenal and Chelse… [+183 chars]"
)